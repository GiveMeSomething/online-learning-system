/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package subject;

import common.entities.Category;
import common.entities.Course;
import common.entities.CourseStatus;
import common.utilities.Controller;
import course.CourseService;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class SubjectController extends HttpServlet implements Controller {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            List<Category> categories = courseService.getAllCategory();
            HashMap<Integer, String> owner = courseService.getOwners();
            request.setAttribute("category", categories);
            request.setAttribute("owner", owner);
            request.getRequestDispatcher("new-subject.jsp").forward(request, response);
        } else if (operation.equals("ADDNEWSUBJECT")) {
            addNewSubject(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void addNewSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = request.getParameter("previousPage");
        String subjectName = request.getParameter("subject-name");
        String category = request.getParameter("category");
        CourseStatus status = CourseStatus.valueOf(request.getParameter("status"));
        Boolean feature = request.getParameter("featured") != null;
        String description = request.getParameter("description");
        
        InputStream inputStream = null;
        Part thumbnail = request.getPart("thumbnail");
        if (thumbnail != null) {
            // prints out some information for debugging
            System.out.println(thumbnail.getName());
            System.out.println(thumbnail.getSize());
            System.out.println(thumbnail.getContentType());
             
            // obtains input stream of the upload file
            inputStream = thumbnail.getInputStream();
        }
        int ownerId = Integer.parseInt(request.getParameter("owner"));

        Course course = new Course(subjectName, description, ownerId, status, category, feature);
        if (courseService.checkCourseExist(subjectName, Integer.parseInt(category)) != null) {
            this.forwardErrorMessage(request, response, "Already had this course", forwardTo);
        } else {
            courseService.addNewSubject(course, inputStream);
            // Navigating to subject list
            response.sendRedirect("new-subject.jsp");
        }
    }
}
