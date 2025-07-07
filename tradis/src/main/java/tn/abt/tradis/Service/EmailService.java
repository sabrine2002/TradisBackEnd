//package tn.abt.tradis.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    public void sendAccountCreationEmail(String to, String firstName, String lastName, String email, String password) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(to);
//        helper.setSubject("Your Tradis Account Details");
//        helper.setText(
//                "<h1>Welcome to Tradis, " + firstName + " " + lastName + "!</h1>" +
//                        "<p>Your account has been created successfully. Below are your login details:</p>" +
//                        "<p><strong>Email:</strong> " + email + "</p>" +
//                        "<p><strong>Password:</strong> " + password + "</p>" +
//                        "<p>Please change your password after logging in for security purposes.</p>",
//                true
//        );
//
//        mailSender.send(message);
//    }}