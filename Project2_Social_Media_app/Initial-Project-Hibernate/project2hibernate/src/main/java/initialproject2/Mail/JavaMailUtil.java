package initialproject2.Mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import initialproject2.model.Mail;

@Service("javaMailUtil")
public class JavaMailUtil {
	
	public static void RecoverEmail(Mail email, String token) {

	// Recipient's email ID needs to be mentioned.
    String to = email.getEmail();

    // Sender's email ID needs to be mentioned
    String from = "firenationapp@gmail.com";

    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";
    
    String body = "<h3>The Following is your Token: " + token + " </h3><br><br>";
    body += "<h1>Please click the following link to resest your password</h1><br>";
    body += "<a href=\"http://localhost:3000/resetPassword\">Reset your Password</a>";
    
    Properties properties = System.getProperties();
    
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");
    
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication("firenationapp@gmail.com", "firenation");

        }

    });

    // Used to debug SMTP issues
    session.setDebug(true);

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject("Recover your password of the fire nation app");

        // Now set the actual message
        //message.setText("This is actual message");
        message.setText(body, "UTF-8", "html");

        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
    
	}   
}
