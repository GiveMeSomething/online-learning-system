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
            String oldPassword = request.getParameter("current-password");
            String newPassword = request.getParameter("new-password");
            String confirmPassword = request.getParameter("confirm-password");

            System.out.println("Run in AuthController");

            processChangePassword(request, response, oldPassword, newPassword, confirmPassword);
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, String email, String password)
            throws ServletException, IOException {
        // Get token from cookies
        String cookieToken = getToken(request);
        String forwardTo = request.getParameter("previousPage");

        Account userAccount = new Account(email, password);
        String accountToken = authService.login(userAccount);

        if (accountToken == null) {
            System.out.println(forwardTo);
            this.forwardErrorMessage(request, response, "Login failed", forwardTo);
            return;
        }

        // Check if the user enter the right account but there is no token in cookie
        if (cookieToken == null || !cookieToken.equals(accountToken)) {
            String confirmEmailPath = "/email?operation=AUTH&receiver=" + email;
            response.sendRedirect(confirmEmailPath);
        } else {
            User currentUser = userService.getUser(email);
            addUserToSession(request, response, currentUser);
            response.sendRedirect(forwardTo);
        }
    }

    private void processRegister(HttpServletRequest request, HttpServletResponse response, Account userAccount, User user)
            throws ServletException, IOException {
        // Add new account and user. Then set token to user's browser's cookie
        String userToken = addAccount(request, response, userAccount, user);
        if (userToken != null) {
            addTokenToCookie(response, userToken);

            // Forward to send confirm email (using email and userToken)
            String confirmEmailPath = "/email?operation=CONFIRM&receiver=" + userAccount.getEmail() + "&token=" + userToken;
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        }
    }

    private void processChangePassword(HttpServletRequest request, HttpServletResponse response,
            String oldPassword, String newPassword, String confirmPassword) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String forwardTo = request.getParameter("previousPage");
        boolean isChanged = false;
        if (currentUser != null && newPassword.equals(confirmPassword)) {
            if (authService.checkCurrentPass(currentUser.getEmail(), oldPassword)) {
                isChanged = authService.changePassword(currentUser.getEmail(), newPassword);
            }
        }

        if (isChanged) {
            response.sendRedirect("home");
        } else {
            this.forwardErrorMessage(request, response, "Can't change password. Please check again later", forwardTo);
        }
    }

    private String addAccount(HttpServletRequest request, HttpServletResponse response, Account userAccount, User user)
            throws ServletException, IOException {
        String token = authService.register(userAccount);
        String forwardTo = request.getParameter("previousPage");

        if (token == null || token.equals("")) {
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
