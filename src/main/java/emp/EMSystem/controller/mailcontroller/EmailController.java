package emp.EMSystem.controller.mailcontroller;

import emp.EMSystem.dto.EmailRequest;
import emp.EMSystem.service.mailservice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email/send")
    public ResponseEntity<?> sendEmail(@RequestBody emp.EMSystem.dto.EmailRequest request) {

        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());


        return ResponseEntity.ok("Email send successfully !!!");
    }

    @PostMapping("/email/sendfile")
    public ResponseEntity<?> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException {
        // Convert MultipartFile to File
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        try {
            emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(), tempFile);
        } finally {
            // Delete the temporary file after use
            System.out.println(request);
            tempFile.delete();
        }
        return ResponseEntity.ok("Email send successfully !!!");

    }


}
