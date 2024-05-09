package helper;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
	
	public static void sendMail(String recipientMailId, String subject, String body) {
		 
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        String emailId = "yihanlam2004@gmail.com"; 	     // admin/company mail-id
        String password = "blppdbuzitcauior";		// generated password

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailId, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailId));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientMailId));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
            System.out.println("Message Sent Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
