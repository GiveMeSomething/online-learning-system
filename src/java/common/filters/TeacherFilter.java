/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.filters;

import auth.AuthService;
import common.entities.Account;
import common.entities.Role;
import common.entities.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class TeacherFilter implements Filter {

    private AuthService authService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest pageRequest = (HttpServletRequest) request;
        HttpServletResponse pageResponse = (HttpServletResponse) response;
        HttpSession currentSession = pageRequest.getSession();

        User user = (User) currentSession.getAttribute("user");

        if (user == null || user.getEmail() == null) {
            pageResponse.sendRedirect(pageRequest.getContextPath() + "/home");
            return;
        }

        authService = new AuthService();
        try {
            Account account = authService.getAccount(user.getEmail());

            // Admin can also access teacher's feature
            if (account != null && (account.getRole() == Role.TEACHER || account.getRole() == Role.ADMIN)) {
                chain.doFilter(request, response);
                return;
            }

            pageResponse.sendRedirect(pageRequest.getContextPath() + "/home");
        } catch (Exception e) {
            pageResponse.sendRedirect(pageRequest.getContextPath() + "/home");
        }
    }
}
