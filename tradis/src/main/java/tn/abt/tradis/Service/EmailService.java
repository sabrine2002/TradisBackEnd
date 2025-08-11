package tn.abt.tradis.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAccountCreationEmail(String toEmail, String username, String password) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject("Compte Attijari Bank : bienvenue et informations de connexion");

        String htmlMsg = "<html><body>"
                + "<h2> Nous avons le plaisir de vous informer que votre compte  a été créé avec succès !</h2>"
                + "<p><b>Nom d'utilisateur:</b> " + username + "</p>"
                + "<p><b>Mot de passe temporaire:</b> " + password + "</p>"
                + "<p>Pour des raisons de sécurité, nous vous invitons à vous connecter dès que possible et à modifier votre mot de passe.</p>"
                + "<p>    Si vous avez des questions ou besoin d’assistance, n’hésitez pas à nous contacter .</p>"
                + "<br>"
                + "<p>Bien cordialement,<br>Attijari Bank</p>"
                + "<img src='cid:logoImage' alt='Logo Attijari' style='width:150px;height:auto;'/>"
                + "</body></html>";

        helper.setText(htmlMsg, true);

        // Chargement de l’image logo depuis ressources (src/main/resources/static/logo.png)
        ClassPathResource logo = new ClassPathResource("assets/Logo_Attijari_bank.png");
        helper.addInline("logoImage", logo);

        mailSender.send(message);
    }
}
