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
        String operation = request.getParameter("operation");

        if (operation.equals("LOGOUT")) {
            HttpSession session = request.getSession();
            session.setAttribute("isAdmin", false);
            session.removeAttribute("user");
        }
        response.sendRedirect("home");
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
            processChangePassword(request, response, oldPassword, newPassword, confirmPassword);
        } else if (operation.equals("RESETPW1")) {
            // Test how this RESETPW1 works
            String email = request.getParameter("email");
            if (authService.getAccount(email) == null) {
                this.forwardErrorMessage(request, response, "Email cannot be found", "nauth/resetPassword1.jsp");
            }
            String resetEmailPath = "/email?operation=RESETPW&receiver=" + email + "&token=";
            String reset = "/email?work=RESETPW&email=" + email;

            // Push reset into Session
            HttpSession ses = request.getSession();
            ses.setAttribute("resetPath", reset);
            // Set time for this session, hope this gonna work!
            ses.setMaxInactiveInterval(30);

            request.getRequestDispatcher(resetEmailPath).forward(request, response);
        } else if (operation.equals("RESETPW2")) {
            // Test changing password
            String newPassword = request.getParameter("new-password");
            String confirmPassword = request.getParameter("confirm-password");
            String email = request.getParameter("email");
            processResetPassword(request, response, newPassword, confirmPassword, email);
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, String email, String password)
            throws ServletException, IOException {
        // Get token from cookies
        String cookieToken = getToken(request, email);
        String forwardTo = request.getParameter("previousPage");

        Account userAccount = new Account(email, password);
        String accountToken = authService.login(userAccount);

        // Check if the user enter the right account but there is no token in cookie
        if (cookieToken == null || !cookieToken.equals(accountToken)) {
            String confirmEmailPath = "/email?operation=AUTH&receiver=" + email;
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        } else {
            // Process if login by admin account
            HttpSession session = request.getSession();

            User currentUser = userService.getUser(email);
            userAccount = authService.getAccount(email);
            addUserToSession(request, response, currentUser);

            if (userAccount.getRole() == Role.ADMIN) {
                session.setAttribute("isAdmin", true);
                session.setAttribute("isTeacher", false);
                response.sendRedirect("auth/admin");
                return;
            } else if (userAccount.getRole() == Role.TEACHER) {
                // Student or Teacher
                session.setAttribute("isAdmin", false);
                session.setAttribute("isTeacher", true);
            } else {
                session.setAttribute("isAdmin", false);
                session.setAttribute("isTeacher", false);
            }
            response.sendRedirect(forwardTo);
        }
    }

    private void processRegister(HttpServletRequest request, HttpServletResponse response, Account userAccount, User user)
            throws ServletException, IOException {
        // Add new account and user. Then set token to user's browser's cookie
        String userToken = addAccount(request, response, userAccount, user);
        if (userToken != null) {
            addTokenToCookie(response, userToken, user.getEmail());

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
            this.forwardErrorMessage(request, response, "Change password successfully", forwardTo);
        } else {
            this.forwardErrorMessage(request, response, "Can't change password. Please check again later", forwardTo);
        }
    }

    //Vu Duy Anh: In Progress (bug: after changed password, still click to the link and change again)
    private void processResetPassword(HttpServletRequest request, HttpServletResponse response,
            String newPassword, String confirmPassword, String email) throws ServletException, IOException {
        String forwardTo = request.getParameter("previousPage");
        boolean isChanged = false;

        if (authService.getAccount(email) != null && newPassword.equals(confirmPassword)) {
            isChanged = authService.changePassword(email, newPassword);
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

    private void addTokenToCookie(HttpServletResponse response, String token, String email) {
        // Put userToken into cookie for later authorization
        Cookie userCookie = new Cookie("ols-token-" + email.hashCode(), token);
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

    private String getToken(HttpServletRequest request, String email) {
        Cookie userCookies[] = request.getCookies();

        // Get required token from user browser cookie
        String requiredCookieName = "ols-token-" + email.hashCode();
        for (Cookie cookie : userCookies) {
            if (cookie.getName().equals(requiredCookieName)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
