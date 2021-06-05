/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package auth;

import common.entities.Account;
import common.entities.Gender;
import common.entities.Role;
import common.entities.Status;
import common.entities.User;
import common.utilities.Controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserService;

public class AuthController extends HttpServlet implements Controller {

    private AuthService authService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("LOGIN")) {
            // Login info
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            processLogin(request, response, email, password);
        } else if (operation.equals("REGISTER")) {
            // Register info
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String mobile = request.getParameter("mobile");
            String role = request.getParameter("role");

            Account userAccount = new Account(email, password, Role.valueOf(role));
            User user = new User(name, Gender.valueOf(gender), email, Status.INACTIVE, mobile);

            processRegister(request, response, userAccount, user);
        } else if (operation.equals("CHANGEPW")) {

        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, String email, String password)
            throws ServletException, IOException {
        // If no token is found, send confirm email else do normal login
        String userToken = getToken(request);

        if (userToken == null || userToken.equals("")) {
            String confirmEmailPath = "/email?work=AUTH&receiver=" + email;
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        } else {
            Account userAccount = new Account(email, password, userToken);
            User currentUser = authService.login(userAccount);

            addUserToSession(request, response, currentUser);
            response.sendRedirect("auth/auth-success.jsp");
        }
    }

    private void processRegister(HttpServletRequest request, HttpServletResponse response, Account userAccount, User user)
            throws ServletException, IOException {
        // Add new account and user. Then set token to user's browser's cookie
        String userToken = addAccount(request, response, userAccount, user);
        if (userToken != null) {
            addTokenToCookie(response, userToken);

            // Forward to send confirm email (using email and userToken)
            String confirmEmailPath = "/email?work=CONFIRM&receiver=" + email + "&token=" + userToken;
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        }
    }

    private String addAccount(HttpServletRequest request, HttpServletResponse response, Account userAccount, User user)
            throws ServletException, IOException {
        String token = authService.register(userAccount);
        String forwardTo = request.getParameter("previousPage");
        System.out.println(token);
        System.out.println(forwardTo);
        if (token == null || token.equals("")) {
            System.out.println("redirect to blah blah");
            this.forwardErrorMessage(request, response, "Register failed. Can't add account", forwardTo);
        } else {
            addUser(request, response, user);
        }

        return token;
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        boolean isUserAdded = userService.addUser(user);
        String forwardTo = request.getParameter("previousPage");
        if (!isUserAdded) {
            this.forwardErrorMessage(request, response, "Register failed. Can't add new user", forwardTo);
        }
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        // Put userToken into cookie for later authorization
        Cookie userCookie = new Cookie("ols-token", token);
        response.addCookie(userCookie);
    }

    private void addUserToSession(HttpServletRequest request, HttpServletResponse response, User currentUser)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String forwardTo = request.getParameter("previousPage");
        if (currentUser == null || currentUser.getEmail() == null) {
            this.forwardErrorMessage(request, response, "Login failed", forwardTo);
        } else {
            currentSession.setAttribute("user", currentUser);
        }
    }

    private String getToken(HttpServletRequest request) {
        Cookie userCookies[] = request.getCookies();

        // Get required token from user browser cookie
        String requiredCookieName = "ols-token";
        for (Cookie cookie : userCookies) {
            if (cookie.getName().equals(requiredCookieName)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
