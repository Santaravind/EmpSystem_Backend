package emp.EMSystem.service.mailservice;

import emp.EMSystem.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mail;

    public void sendEmailWithHtml(String to, String subject, String htmlContent) {

        MimeMessage simpleMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(mail);
            helper.setText(htmlContent, true);
            mailSender.send(simpleMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }
    }

    public void sendEmailWithFile(String to, String subject, String message, File file) {

        MimeMessage simpleMessage=mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper=new MimeMessageHelper(simpleMessage,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(mail);
            helper.setText(message,true);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(simpleMessage);
        }
        catch ( MessagingException e){
            throw  new RuntimeException(e);

        }
    }


  // For Forgot password



    public void simpleMailForForgetpassoer(EmailRequest mailBody) {

        SimpleMailMessage message =new SimpleMailMessage();

        message.setTo(mailBody.getTo());
        message.setFrom(mail);
        message.setSubject(mailBody.getSubject());
        message.setText(mailBody.getMessage());

        mailSender.send(message);


    }


}
