/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package email;

import common.utilities.Emailer;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailController extends HttpServlet {

    private String host;
    private String port;
    private String email;
    private String password;

    private EmailService emailService;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        password = context.getInitParameter("email_password");

        // Init Service
        emailService = new EmailService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Reads request data
        String receiver = request.getParameter("receiver");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        boolean isSent = emailService.sendEmail(
                host, port, host, password,
                receiver, subject, content
        );

        if (isSent) {
            // Do something
        } else {
            // Do something else
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
