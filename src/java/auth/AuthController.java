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
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserService;

public class AuthController extends HttpServlet {

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
        // Login info
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Register info
        String name = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String role = request.getParameter("role");

        Account userAccount = new Account(email, password, Role.valueOf(role));

        String token;
        boolean isUserAdded;
        // Register
        if (role != null || !role.equals("")) {
            User registerUser = new User(name, Gender.valueOf(gender), email, Status.INACTIVE, mobile);

            // Add new account and new user in DB
            token = authService.register(userAccount);
            isUserAdded = userService.addUser(registerUser);

            // Put token into cookie for later authorization
            Cookie userCookie = new Cookie("ols-token", token);
            response.addCookie(userCookie);

            System.out.println(token + " in AuthController");
            System.out.println(isUserAdded + " in AuthController");

            // Forward to send confirm email (using email and token)
            String confirmEmailPath = "/email?work=CONFIRM&receiver=" + email + "&token=" + token;
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        } else {

        }
    }
}
