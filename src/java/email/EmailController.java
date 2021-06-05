/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package email;

import auth.AuthService;
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
    private AuthService authService;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        password = context.getInitParameter("email_password");

        // Init Service
        emailService = new EmailService();
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("CONFIRM")) {
            // Reads request data
            String userEmail = request.getParameter("email");
            String token = request.getParameter("token");

            // Request to authService to active current user
            boolean isActiveSuccess = authService.activeAccount(userEmail, token);
            if (isActiveSuccess) {
                response.sendRedirect("/home");
            } else {
                // Do something when active fail
            }

        } else if (operation.equals("CHANGEPW")) {
            // TODO: Duy Anh se implement phan nay
        } else {
            response.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get operation from request
        String operation = request.getParameter("operation");

        if (operation.equals("CONFIRM")) {
            // Reads request data
            String inputEmail = request.getParameter("receiver");
            String token = request.getParameter("token");
            String forwardTo = request.getParameter("previousPage");
            boolean isSent = emailService.sendConfirmEmail(host, port, email, password, inputEmail, token);

            if (isSent) {
                // Do something
                System.out.println("Email sent");
            } else {
                // Do something else
                System.out.println("Can't send");
            }

            response.sendRedirect(forwardTo);
        } else if (operation.equals("CHANGEPW")) {
            // TODO: Duy Anh se implement phan nay
        }
    }
}
