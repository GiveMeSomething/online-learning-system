/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

import java.io.UnsupportedEncodingException;
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
            String email, String password, String receiver,
            String subject, String emailContent) throws AddressException,
            MessagingException,
            UnsupportedEncodingException {

        // Sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.user", email);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

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

        message.setFrom(new InternetAddress("olslearningplatform@gmail.com", "Learning OLS"));
        InternetAddress[] toAddresses = {new InternetAddress(receiver)};
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(emailContent);

        // Sends the e-mail
        Transport transport = session.getTransport("smtp");
        transport.connect(host, email, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
