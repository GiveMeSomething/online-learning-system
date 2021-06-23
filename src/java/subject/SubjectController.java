/**
 * Jun 22, 2021
 *
 * @author Dinh Kong Thanh
 */
package subject;
import common.entities.Category;
import common.entities.Course;
import common.entities.PricePack;
import common.entities.Status;
import common.entities.User;
import course.CourseService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserService;
public class SubjectController extends HttpServlet {

    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            request.setAttribute("activeId", 1);
            String subjectId = request.getParameter("subjectId");
            Course subject = courseService.getSubject(2);
            User ownerName = userService.getUserById(subject.getOwnerId());
            List<Category> categoryList = courseService.getAllCategory();
            List<User> authorList = userService.getAuthor();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("ownerName", ownerName);
            request.setAttribute("authorList", authorList);
            request.setAttribute("detail", subject);
            listPackagePrice(request, response);
            request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
        } else {
            switch (operation) {
                case "addPackage":
                    addPackage(request, response);
                    break;
                case "deletePackage":
                    deletePackage(request, response);
                    break;
                case "editPackage":
                    editPackage(request, response);
                    break;
                default:
                    listPackagePrice(request, response);
                    break;
            }
        }
    }

    private void listPackagePrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseService service = new CourseService();
        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        List<PricePack> listPackage = service.getPricePackage(Integer.parseInt(index));
        int endPage = (service.countTotalPricePackage() / 5) + (service.countTotalPricePackage() % 5 == 0 ? 0 : 1);
        request.setAttribute("index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listP", listPackage);
        request.setAttribute("activeId", 3);
        request.setAttribute("totalPackage", service.countTotalPricePackage());
        request.setAttribute("totalPackageOfPage", listPackage.size());
        request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
    }

    private void addPackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("namepackage");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String descriptions = request.getParameter("descriptions");

        CourseService service = new CourseService();
        service.addPackage(duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=LISTPACKAGE&page=1");
    }

    private void deletePackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        CourseService service = new CourseService();
        service.deletePackage(id);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=LISTPACKAGE&page=1");
    }

    private void editPackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("eId"));
        String name = request.getParameter("editName");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String descriptions = request.getParameter("descriptions");

        CourseService service = new CourseService();
        service.editPackage(id, duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=LISTPACKAGE&page=1");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        String operation = request.getParameter("operation");
        String subjectId = request.getParameter("subjectId");
        Course subject = courseService.getSubject(2);

        if (operation.equals("changeSubjectInformation")) {
            int id = subject.getId();
            String subjectName = request.getParameter("subjectName");
            int categoryBox = Integer.parseInt(request.getParameter("categoryBox"));
            String[] isFeatured = request.getParameterValues("featuredSubject");
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            int courseOwner = Integer.parseInt(request.getParameter("courseOwner"));
            if (isFeatured != null && isFeatured[0] != null) {
                courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 1, 2);
            } else {
                courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 0, 2);
            }
            request.setAttribute("activeId", 1);
            response.sendRedirect(request.getContextPath() + "/auth/teacher/subject");
        }
    }
}