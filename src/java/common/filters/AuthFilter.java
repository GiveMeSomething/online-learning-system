/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package common.filters;

import auth.AuthService;
import common.entities.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authService = new AuthService();
    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest pageRequest = (HttpServletRequest) request;
        HttpServletResponse pageResponse = (HttpServletResponse) response;
        HttpSession currentSession = pageRequest.getSession();

        // To 'login' if user not yet logged in, else check token (authorization)
        User currentUser = getUser(currentSession);
        if (currentUser == null) {
            pageResponse.sendRedirect(pageRequest.getContextPath() + "/home");
            return;
        }

        String token = getToken(pageRequest, currentSession);

        // If no token is found, send confirm email
        if (token == null || token.equals("")) {
            String confirmEmailPath = pageRequest.getContextPath() + "/email?operation=AUTH&receiver=" + currentUser.getEmail();
            request.getRequestDispatcher(confirmEmailPath).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    // Get token with logic (get from session or cookie)
    private String getToken(HttpServletRequest request, HttpSession session) {
        // Check if token is in session, else get from cookie and auth
        User currentUser = (User) session.getAttribute("user");
        String token;
        try {
            token = (String) session.getAttribute("ols-token");
            if (token == null || token.equals("")) {
                // Get token from cookie
                token = getToken(request);
                if (authService.isValidToken(currentUser.getEmail(), token)) {
                    return token;
                }

                return null;
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage() + " at AuthFilter ");
            return null;
        }

        return token;
    }

    // Get token from cookie
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

    private User getUser(HttpSession currentSession) {
        User currentUser = (User) currentSession.getAttribute("user");
        if (currentUser != null && currentUser.getEmail() != null) {
            return currentUser;
        }

        return null;
    }

}
