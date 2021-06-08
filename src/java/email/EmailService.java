/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package email;

import common.utilities.Emailer;

public class EmailService {

    public boolean sendConfirmEmail(String host, String port, String email, String password, String receiver, String token) {
        try {
            String subject = "OLS Account Confirmation";
            String content = "http://localhost:8080/online-learning-system/email?work=CONFIRM&email=" + receiver + "&token=" + token;
            Emailer.sendEmail(host, port, email, password, receiver, subject, content);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    // Test send email for resetting password
    public boolean sendResetPasswordEmail(String host, String port, String email, String password, String receiver) {
        try {
            String subject = "OLS Account Confirmation";
            String content = "http://localhost:8080/online-learning-system/email?work=RESETPW&email=" + receiver;
            Emailer.sendEmail(host, port, email, password, receiver, subject, content);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
