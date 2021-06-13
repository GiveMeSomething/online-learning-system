/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package email;

import auth.AuthService;
import common.entities.Account;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String operation = request.getParameter("work");

        if (operation.equals("CONFIRM")) {
            // Reads request data
            String userEmail = request.getParameter("email");
            String token = request.getParameter("token");

            // Request to authService to active current user
            boolean isActiveSuccess = authService.activeAccount(userEmail, token);
            if (isActiveSuccess) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                // Do something when active fail
                response.sendRedirect("nauth/authenticate/register-failed.jsp");
            }

        } else if (operation.equals("RESETPW")) {
            // Test if navigate to Change password page in nauth
            String email = request.getParameter("email");
            request.setAttribute("email", email);
            request.getRequestDispatcher("nauth/resetPassword2.jsp").forward(request, response);
        } else if (operation.equals("RESETPW1")) {
            // Test if navigate to Change password page in nauth
            String resetEmail = request.getParameter("email");
            request.setAttribute("email", email);
            HttpSession ses = request.getSession(false);
            Object obj = ses.getAttribute("resetPath");
            
            request.getRequestDispatcher((String) obj).forward(request, response);
        } else if (operation.equals("AUTH")) {
            // Reads request data
            String userEmail = request.getParameter("email");
            String cryptPassword = request.getParameter("password");

            String token = authService.getToken(userEmail, cryptPassword);
            addTokenToCookie(response, token);

            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
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
        } else if (operation.equals("RESETPW")) {
            // Test if send successfully
            String thisEmail = request.getParameter("email");
            String token = request.getParameter("token");
            boolean isSent = emailService.sendResetPasswordEmail(host, port, email, password, thisEmail, token);
//            processConfirm(request, response);
        } else if (operation.equals("AUTH")) {
            processAuth(request, response);
        } else {
            response.sendRedirect("/home");

        }
    }

    public void processConfirm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Reads request data
        String inputEmail = request.getParameter("receiver");
        String token = request.getParameter("token");
        boolean isSent = emailService.sendConfirmEmail(host, port, email, password,
                inputEmail, token);

        if (isSent) {
            // Do something
            System.out.println("Email sent");
        } else {
            // Do something else
            System.out.println("Can't send");
        }

        response.sendRedirect("nauth/authenticate/register-success.jsp");
    }

    public void processAuth(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("email");

        Account authAccount = authService.getAccount(userEmail);
        boolean isSent = emailService.sendAuthEmail(host, port, email, password,
                authAccount.getEmail(), authAccount.getPassword());

        if (isSent) {
            // Do something
            System.out.println("Email sent");
        } else {
            // Do something else
            System.out.println("Can't send");
        }

        response.sendRedirect("nauth/authenticate/auth-success.jsp");
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        // Put userToken into cookie for later authorization
        Cookie userCookie = new Cookie("ols-token", token);
        response.addCookie(userCookie);
    }
}
