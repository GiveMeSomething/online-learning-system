/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

import java.util.Date;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {

    public static void sendEmail(String host, String port,
            final String email, final String password, String toAddress,
            String subject, String emailContent) throws AddressException,
            MessagingException {

        // Sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // Creates a new e-mail message
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(email));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(emailContent);

        // sends the e-mail
        Transport.send(message);

    }
}
