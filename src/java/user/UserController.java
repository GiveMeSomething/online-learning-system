/**
 * Jun 3, 2021
 *
 * @author Nguyen Khanh Toan
 */
package user;

import common.entities.Category;
import common.entities.Gender;
import common.entities.Status;
import common.entities.User;
import common.utilities.Controller;
import home.HomeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet implements Controller {

    private UserService userService;
    private HomeService homeService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        // ĐANG HARDCODE
        int id = 1;
        currentSession.setAttribute("id", id);
        
        User user = userService.getUserProfile(id);
        request.setAttribute("user", user);
        List<Category> listC = homeService.getAllCategory();
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String operation = request.getParameter("operation");

        if(operation.equals("changeUserProfile")){
            int id = Integer.parseInt(currentSession.getAttribute("id") + "");
            String image = request.getParameter("image");
            String fullName = request.getParameter("fName");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String status = request.getParameter("status");
            String mobile = request.getParameter("mobile");
            User userUpdate = new User(id, image, fullName, Gender.valueOf(gender), email,
                    address, Status.valueOf(status), mobile);
            boolean isUpdate = userService.updateUserProfile(userUpdate);
            if (isUpdate) {
                response.sendRedirect("user");
            } else {
                this.forwardErrorMessage(request, response, "Can not update", "user");
            } 
        }
        
    }
}
