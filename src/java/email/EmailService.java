/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package email;

import common.utilities.Emailer;

public class EmailService {

    public boolean sendEmail(String host, String port, String email, String password,
            String receiver, String subject, String content) {
        try {
            Emailer.sendEmail(host, port, email, password, receiver, subject, content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
