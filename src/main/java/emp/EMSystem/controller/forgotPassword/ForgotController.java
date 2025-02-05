package emp.EMSystem.controller.forgotPassword;

import emp.EMSystem.config.ChangePassword;
import emp.EMSystem.dto.EmailRequest;

import emp.EMSystem.model.ForgotPassword;
import emp.EMSystem.model.OurUsers;
import emp.EMSystem.repository.ForgotPasswordRepositery;
import emp.EMSystem.repository.UserRepo;
import emp.EMSystem.service.mailservice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/forgotpassword")
public class ForgotController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
   private   ForgotPasswordRepositery forgotPasswordRepositery;

    @Autowired
    private PasswordEncoder passwordEncoder;



    //sent mail for email verification

    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email){

        OurUsers user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Please give me valid email!!"));

        int otp=otpGenerator();

        // Check if the user already has a ForgotPassword record
        Optional<ForgotPassword> existingFpOpt = forgotPasswordRepositery.findByUser(user);

        if (existingFpOpt.isPresent()) {
            // If an OTP record exists, update it
            ForgotPassword existingFp = existingFpOpt.get();
            existingFp.setOtp(otp);
            existingFp.setExpirationTime(new Date(System.currentTimeMillis() + 90 * 1000));
            forgotPasswordRepositery.save(existingFp); // Save the updated record
        } else {
            // If no OTP record exists, create a new one
            ForgotPassword fp = ForgotPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() + 180 * 1000))
                    .user(user)
                    .build();

            forgotPasswordRepositery.save(fp);
        }

        EmailRequest mailBody=EmailRequest.builder()
                .to(email)
                .message("This is the otp for your Forgot Password request : " + otp)
                .subject("OTP for Forgot Password request").build();

//       // ForgotPassword fp= ForgotPassword.builder()
//                .otp(otp)
//                .expirationTime(new Date(System.currentTimeMillis()+60*1000))
//                .user(user)
//                .build();
        // Check if the user already has a forgot password record
//        ForgotPassword existingFp = forgotPasswordRepositery.findByUser(user).orElse(null);
//
//        if (existingFp != null) {
//            // If an OTP record already exists, delete the old one
//            forgotPasswordRepositery.delete(existingFp);
//        }
//        ForgotPassword fp=ForgotPassword.builder()
//                .otp(otp)
//                .expirationTime(new Date(System.currentTimeMillis()+180*1000))
//                .user(user)
//                .build();

        emailService.simpleMailForForgetpassoer(mailBody);
        //forgotPasswordRepositery.save(fp);

        return  ResponseEntity.ok("Email send for verification !!!");

    }

private Integer otpGenerator(){
    Random random=new Random();
    return random.nextInt(100_000,999_999);

}

@PostMapping("/verifyOtp/{otp}/{email}")
public ResponseEntity<String> verifyOtp(@PathVariable Integer otp,@PathVariable String email){
    OurUsers user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Please give me valid email!!"));

  ForgotPassword fp=  forgotPasswordRepositery.findByOtpAndUser(otp,user).orElseThrow(()->new RuntimeException("Invalid OTP for email:"+email));

    if (fp.getExpirationTime().before( Date.from(Instant.now()))){
        forgotPasswordRepositery.deleteById(fp.getFpid());
        return new ResponseEntity<>("OTP  has expired ", HttpStatus.EXPECTATION_FAILED);
    }

    return ResponseEntity.ok("Verify your email compiled !!");

}

 @PostMapping("/changepassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@PathVariable String email ,@RequestBody ChangePassword changePassword){
        if (!Objects.equals(changePassword.password(),changePassword.password())){
            return new ResponseEntity<>("Please enter the password again !!",HttpStatus.EXPECTATION_FAILED);
        }

        String encoderPassword =passwordEncoder.encode(changePassword.password());
        userRepo.updatePassword(email,encoderPassword);


       return ResponseEntity.ok("Password has been changed!");
 }



}
