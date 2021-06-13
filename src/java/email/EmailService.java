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
        }
        return false;
    }

    public boolean sendAuthEmail(String host, String port, String email, String password, String receiver, String cryptPassword) {
        try {
            String subject = "OLS Account Confirmation";
            String content = "http://localhost:8080/online-learning-system/email?work=AUTH&email=" + receiver + "&password=" + cryptPassword;
            Emailer.sendEmail(host, port, email, password, receiver, subject, content);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Test send email for resetting password
    public boolean sendResetPasswordEmail(String host, String port, String email, String password, String receiver, String token) {
        try {
            String subject = "OLS Reset Password";
            String content = "http://localhost:8080/online-learning-system/email?work=RESETPW1&email=" + receiver+"&token="+token;
            Emailer.sendEmail(host, port, email, password, receiver, subject, content);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
