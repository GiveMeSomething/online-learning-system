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
import course.CourseService;
import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class UserController extends HttpServlet implements Controller {

    private UserService userService;
    private CourseService courseService;
    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "assets/";

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        courseService = new CourseService();
    }
    
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession currentSession = request.getSession();
        User userSession = (User) currentSession.getAttribute("user");

        User user = userService.getUserProfile(userSession.getId());
        request.setAttribute("image", user.getImage());


        List<Category> categoryList = courseService.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/auth/user/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String operation = request.getParameter("operation");
        User currentUser = (User) currentSession.getAttribute("user");

        if (operation.equals("changeUserProfile")) {
            int id = currentUser.getId();
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
                userUpdate = userService.getUserById(id);
                currentSession.setAttribute("user", userUpdate);
                response.sendRedirect(request.getContextPath()+"/auth/user/");
            } else {
                this.forwardErrorMessage(request, response, "Can not update", "user");
            }
        }else if(operation.equals("UPLOADAVATAR")){
            request.setAttribute("image", uploadFile(request));
            request.getRequestDispatcher("/auth/user/profile.jsp").forward(request, response);
        }
    }
}
