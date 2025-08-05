package tn.abt.tradis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAccountCreationEmail(String toEmail, String username, String password) {
        String subject = "Your Attijari Account has been created";
        String body = "Your account has been successfully created.\n\n"
                + "Username: " + username + "\n"
                + "Temporary Password: " + password + "\n\n"
                + "Please log in and change your password as soon as possible.\n\n"
                + "Best regards,\nAttijari Bank";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}