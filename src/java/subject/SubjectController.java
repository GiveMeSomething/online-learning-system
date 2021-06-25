
/*              * Jun 16, 2021
 *
 *
 * @author
    Nguyen Khanh Toan
    * @author
    Vu Duy Anh
 */
package subject;

import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.Status;
import common.entities.User;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import common.entities.Category;
import common.entities.Course;
import common.entities.CourseStatus;
import common.entities.PricePack;
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
import user.UserService;

@MultipartConfig(maxFileSize = 16177215)
public class SubjectController extends HttpServlet implements Controller {

    private UserService userService;
    private CourseService courseService;
    private final int itemPerPage = 5;

    @Override

    public void init() throws ServletException {
        userService = new UserService();
        courseService = new CourseService();
    }

    protected void displayData(HttpServletRequest request, HttpServletResponse response, int subjectId)
            throws ServletException, IOException {
        //Display subject dimesion detail of specified course
        List<Dimension> listDimension = courseService.getSubjectDimensionByCourseId(subjectId);
        request.setAttribute("listDimension", listDimension);
        request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String operation = request.getParameter("operation");
        if (operation == null) {
            processInputForSubject(request, response);
            //listPackagePrice(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    currentSession.setAttribute("currentSubject", request.getParameter("subjectId"));
                    request.setAttribute("activeId", 1);
                    processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
                    displayData(request, response, Integer.parseInt((String) currentSession.getAttribute("currentSubject")));
                    listPackagePrice(request, response);
                    break;
                case "DELETESUBJECT":
                    deleteSubjectDimension(request, response);
                    break;
                case "GETDIMENSIONDETAIL":
                    getDimensionDetail(request, response);
                    break;
                case "PAGINATION":
                    getItemInPage(request, response);
                    break;
                case "TONEWSUBJECT":
                    List<Category> categories = courseService.getAllCategory();
                    HashMap<Integer, String> owner = courseService.getOwners();
                    request.setAttribute("category", categories);
                    request.setAttribute("owner", owner);
                    request.getRequestDispatcher("/auth/admin/subject/new-subject.jsp").forward(request, response);
                    break;
                case "GETSUBJECT":
                    processInputForSubject(request, response);
                    break;
                case "addPackage":
                    addPackage(request, response);
                    break;
                case "deletePackage":
                    deletePackage(request, response);
                    break;
                case "editPackage":
                    editPackage(request, response);
                    break;
                case "list":
                    listPackagePrice(request, response);
                    break;
                default:
                    //response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    listPackagePrice(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        String subjectId = request.getParameter("subjectId");
        HttpSession currentSession = request.getSession();

        switch (operation) {
            case "ADDSUBJECT": {
                addSubjectDimension(request, response);
                processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
                break;
            }
            case "CHANGESUBJECTINFORMATION": {
                String subjectName = request.getParameter("subjectName");
                int categoryBox = Integer.parseInt(request.getParameter("categoryBox"));
                String[] isFeatured = request.getParameterValues("featuredSubject");
                int status = Integer.parseInt(request.getParameter("status"));
                String description = request.getParameter("description");
                int courseOwner = Integer.parseInt(request.getParameter("courseOwner"));
                if (isFeatured != null && isFeatured[0] != null) {
                    courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 1, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
                } else {
                    courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 0, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
                }
                request.setAttribute("activeId", 1);
//                response.sendRedirect("subject");
                processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
                displayData(request, response, Integer.parseInt((String) currentSession.getAttribute("currentSubject")));
                break;
            }
            case "UPDATESUBJECT": {
                updateSubjectDimension(request, response);

                break;
            }
            case "FILTER":
                processInputForSubject(request, response);
                break;
            case "ADDNEWSUBJECT":
                addNewSubject(request, response);
                break;
            case "addPackage":
                addPackage(request, response);
                break;
            case "deletePackage":
                deletePackage(request, response);
                break;
            case "editPackage":
                editPackage(request, response);
                break;
            case "list":
                listPackagePrice(request, response);
                break;
            default:
                //response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                listPackagePrice(request, response);
                break;
        }
    }

    private void deleteSubjectDimension(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int subjectId = Integer.parseInt((String) currentSession.getAttribute("currentSubject"));
        int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
        courseService.deleteSubjectDimensionByCourseId(subjectId, dimensionId);
        request.setAttribute("activeId", 2);
        processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
        displayData(request, response, Integer.parseInt((String) currentSession.getAttribute("currentSubject")));
    }

    private void getDimensionDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
        Dimension dimensionDetail = courseService.getDimensionDetail(dimensionId);
        List<DimensionType> dimensionTypeList = courseService.getAllDimenstionType();

        request.setAttribute("dimensionDetail", dimensionDetail);
        request.setAttribute("dimensionId", dimensionId);
        request.setAttribute("dimensionTypeList", dimensionTypeList);

        request.getRequestDispatcher("/auth/teacher/subject/dimensionEditInfo.jsp").forward(request, response);
    }

    private void addSubjectDimension(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int subjectId = Integer.parseInt((String) currentSession.getAttribute("currentSubject"));
        String type = request.getParameter("type");
        String dimension = request.getParameter("dimension");
        int typeInt = 0;
        switch (type) {
            case "Domain":
                typeInt = 1;
                break;
            case "Group":
                typeInt = 2;
                break;
            default:
                courseService.addDimensionType(type);
                typeInt = courseService.getDimensionTypeDetail(type).getId();
                break;
        }
        String description = request.getParameter("description");
        courseService.addDimension(typeInt, dimension, description);
        Dimension dimensionAdd = courseService.getDimensionId(dimension);
        courseService.addDimensionCourse(subjectId, dimensionAdd.getId());
        request.setAttribute("activeId", 2);
        processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
        displayData(request, response, Integer.parseInt((String) currentSession.getAttribute("currentSubject")));
    }

    private void updateSubjectDimension(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
        String type = request.getParameter("type");
        int typeInt = 0;
        switch (type) {
            case "domain":
                typeInt = 1;
                break;
            case "group":
                typeInt = 2;
                break;
            default:
                typeInt = courseService.getDimensionTypeDetail(type).getId();
                break;
        }
        String dimension = request.getParameter("dimension");
        String description = request.getParameter("description");
        courseService.updateSubjectDimension(typeInt, dimension, description, dimensionId);
        request.setAttribute("activeId", 2);
        processInputForOverview(request, response, Integer.parseInt(currentSession.getAttribute("currentSubject") + ""));
        displayData(request, response, Integer.parseInt((String) currentSession.getAttribute("currentSubject")));
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
            response.sendRedirect(request.getContextPath() + "/auth/teacher/subject");
        }
    }

    private void processInputForOverview(HttpServletRequest request, HttpServletResponse response, int subjectId) throws ServletException, IOException {
//        request.setAttribute("activeId", 1);

        Course subject = courseService.getSubject(subjectId);
        User ownerName = userService.getUserById(subject.getOwnerId());
        List<Category> categoryList = courseService.getAllCategory();
        List<User> authorList = userService.getAuthor();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("ownerName", ownerName);
        request.setAttribute("authorList", authorList);
        request.setAttribute("detail", subject);
//        request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
    }

    private void processInputForSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int categoryId = -1;
        String statusString = request.getParameter("status");
        Status status;

        try {
            String category = request.getParameter("category");
            System.out.println(category);
            categoryId = Integer.parseInt(category);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~60 SubjectController");
        }

        if (statusString != null && !statusString.equals("")) {
            status = Status.valueOf(statusString);
        } else {
            status = null;
        }

        // Save selected value to request
        request.setAttribute("selectedCategory", categoryId);
        request.setAttribute("selectedStatus", statusString);
        request.setAttribute("selectedKeyword", keyword);

        // We don't need to process status because null is consider as status's default value
        processGetSubject(request, response, keyword, categoryId, status);
    }

    private void processGetSubject(HttpServletRequest request, HttpServletResponse response,
            String keyword, int categoryId, Status status) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int teacherId;
        if (currentSession.getAttribute("isAdmin") != null) {
            teacherId = -1;
        } else {
            teacherId = ((User) currentSession.getAttribute("user")).getId();
        }
        ArrayList<ArrayList<String>> subjectList = courseService.getSubjectList(keyword, categoryId, status, teacherId);
        currentSession.setAttribute("subjectList", subjectList);

        // Send data to the list page
        int page = processPageParameter(request, response, subjectList.size());
        ArrayList<ArrayList<String>> pageItems = getItemInPage(subjectList, page);
        if (pageItems != null) {
            request.setAttribute("categories", courseService.getAllCategory());
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/subject/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }

    }

    private void getItemInPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        ArrayList<ArrayList<String>> pageItems = getItemInPage((ArrayList<ArrayList<String>>) currentSession.getAttribute("subjectList"), page);
        if (pageItems != null) {
            request.setAttribute("categories", courseService.getAllCategory());
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/subject/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    // Get the right data for the right page
    private ArrayList<ArrayList<String>> getItemInPage(ArrayList<ArrayList<String>> subjectList, int page) {
        if (page > ((subjectList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > subjectList.size() ? subjectList.size() : startItem + itemPerPage;

        ArrayList<ArrayList<String>> subjectInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            subjectInPage.add(subjectList.get(i));
        }

        return subjectInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));

            int pageNum;
            if (listSize % itemPerPage == 0) {
                pageNum = listSize / itemPerPage;
            } else {
                pageNum = (listSize / itemPerPage) + 1;
            }

            if (page < 1 || page > pageNum) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 LessonController");
        }

        return page;
    }

    private void listPackagePrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        List<PricePack> listPackage = courseService.getPricePackage(Integer.parseInt(index));
        int endPage = (courseService.countTotalPricePackage() / 5) + (courseService.countTotalPricePackage() % 5 == 0 ? 0 : 1);
        request.setAttribute("index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listP", listPackage);
        request.setAttribute("activeId", 3);
        request.setAttribute("totalPackage", courseService.countTotalPricePackage());
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
        courseService.addPackage(duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=list");
    }

    private void deletePackage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseService.deletePackage(id);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=list");
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
        courseService.editPackage(id, duration, name, price, status, descriptions, discount);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/subject?operation=list");
    }

}
