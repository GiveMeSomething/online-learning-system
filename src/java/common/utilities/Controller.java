/**
 * Jun 2, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

    // To send error back to the error page
    // Can be used as default or override to add extra logic
    default void forwardErrorMessage(HttpServletRequest request, HttpServletResponse response,
            String message, String forwardTo)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(forwardTo).forward(request, response);
    }

    default void send404(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
    }
}
