package course;

import auth.AuthService;
import common.entities.Account;
import common.entities.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.entities.Category;
import common.entities.Gender;
import common.entities.PricePackage;
import common.entities.Role;
import common.entities.Status;
import common.entities.User;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import user.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nguyen Khanh Toan
 */
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

    private CourseService courseService;
    private AuthService authService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();

        authService = new AuthService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String categoryId = request.getParameter("cID");
        String operation = request.getParameter("operation");
        if (operation == null) {
            if (courseId != null) {
                Course courseDetail = courseService.getCourse(Integer.parseInt(courseId));
                List<Course> siderCourse = courseService.getSiderCourseDetail();
                List<Category> categoryList = courseService.getAllCategory();
                ArrayList<PricePackage> coursePackages = courseService.getCoursePackage(courseDetail.getId());

                request.setAttribute("detail", courseDetail);
                request.setAttribute("siderCourse", siderCourse);
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("coursePackages", coursePackages);

                request.getRequestDispatcher("nauth/course/detail.jsp").forward(request, response);
            } else if (categoryId != null) {
                int categoryIndicator = Integer.parseInt(categoryId);
                HttpSession session = request.getSession();
                session.setAttribute("categoryId", categoryId);
                List<Course> courseFeature = courseService.getCourseFeature(categoryIndicator);
                String searchName = request.getParameter("searchName");
                //assign first slider image active to display in UI
                int idFeature = 0;
                for (int i = 0; i < courseFeature.size(); i++) {
                    idFeature = courseFeature.get(0).getId();
                }
                //Get title corresponding to category id
                getTitle(request, response, categoryIndicator);
                String index = request.getParameter("index");
                if (index == null) {
                    index = "1";
                }
                int indexPage = Integer.parseInt(index);
                //show the category list dropdown
                List<Category> categoryList = courseService.getAllCategory();
                //filter price,alpha
                String price = request.getParameter("price");
                String alpha = request.getParameter("alpha");
                //handle filter
                handleFilterPriceAlpha(request, response, categoryIndicator, searchName, indexPage, price, alpha);
                session.setAttribute("price", price);
                session.setAttribute("alpha", alpha);
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("tag", index);
                request.setAttribute("price", price);
                if (searchName != null) {
                    request.setAttribute("end", getTotalPageSearch(request, response, categoryIndicator, searchName));
                } else {
                    request.setAttribute("end", getTotalPage(request, response, categoryIndicator));
                }
                request.setAttribute("categoryId", categoryId);
                request.setAttribute("courseFeature", courseFeature);
                session.removeAttribute("searchName");
                request.setAttribute("id", idFeature);
                request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
            }
        } else if (operation.equals("VIEWMYCOURSE")) {
            List<Category> categoryList = courseService.getAllCategory();
            request.setAttribute("categoryList", categoryList);
            getMyCourse(request, response);
            getMyCourseSuccess(request, response);
            request.getRequestDispatcher("/auth/user/course/my-course.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation.equals("REGISTER")) {
            processRegisterCourse(request, response);
        } else if (operation.equals("SEARCHCOURSE")) {
            int categoryId = Integer.parseInt(request.getParameter("cID"));
            String searchName = request.getParameter("searchCourse");
            HttpSession ses = request.getSession();
            ses.setAttribute("searchName", searchName);
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);

            String price = request.getParameter("price");
            String alpha = request.getParameter("alpha");
            List<Course> list = courseService.searchCourse(categoryId, searchName);
            List<Course> courseFeature = courseService.getCourseFeature(categoryId);
            List<Category> categoryList = courseService.getAllCategory();
            request.setAttribute("categoryList", categoryList);
            int id = 0;
            for (int i = 0; i < courseFeature.size(); i++) {
                id = courseFeature.get(0).getId();
            }
            request.setAttribute("id", id);
            request.setAttribute("end", getTotalPageSearch(request, response, categoryId, searchName));
            request.setAttribute("tag", 1);
            request.setAttribute("course", list);
            request.setAttribute("courseFeature", courseFeature);
            request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
        }
    }

    private void handleFilterPriceAlpha(HttpServletRequest request,
            HttpServletResponse response, int categoryIndicator, String searchName,
            int indexPage, String price, String alpha)
            throws ServletException, IOException {
        //When user click both filter by price and alpha
        if (price != null && alpha != null && price != "" && alpha != "") {
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else if (price != null && price != "") { //When user click filter by price
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else if (alpha != null && alpha != "") { //When user click filter by alpha
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else { //When user do not click fiter radio button
            if (price == null && alpha == null) {
                List<Course> listCoursePaging = courseService.pagingCourseList(categoryIndicator, indexPage);
                request.setAttribute("course", listCoursePaging);
            } else { //When paging search
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            }

        }
    }

    private void getTitle(HttpServletRequest request, HttpServletResponse response, int categoryId)
            throws ServletException, IOException {
        switch (categoryId) {
            case 1: {
                request.setAttribute("title", "Software Enginneering");
                break;
            }
            case 2: {
                request.setAttribute("title", "Economy");
                break;
            }
            case 3: {
                request.setAttribute("title", "Digital Marketing");
                break;
            }
            case 4: {
                request.setAttribute("title", "Artificial Intelligence");
                break;
            }
            case 5: {
                request.setAttribute("title", "Information Assurance");
                break;
            }
            case 6: {
                request.setAttribute("title", "Language");
                break;
            }

        }
    }

    private int getTotalPage(HttpServletRequest request, HttpServletResponse response, int categoryId)
            throws ServletException, IOException {
        int total = courseService.countingCourseList(categoryId);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseList(categoryId) / 8;
        } else {
            endPage = (courseService.countingCourseList(categoryId) / 8) + 1;
        }
        return endPage;
    }

    private int getTotalPageSearch(HttpServletRequest request, HttpServletResponse response, int categoryId, String searchName)
            throws ServletException, IOException {
        int total = courseService.countingCourseListSearch(categoryId, searchName);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseListSearch(categoryId, searchName) / 8;
        } else {
            endPage = (courseService.countingCourseListSearch(categoryId, searchName) / 8) + 1;
        }
        return endPage;

    }

    private void getMyCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int userId = ((User) currentSession.getAttribute("user")).getId();

        List<Course> myCourse = courseService.getMyCourse(userId);
        List<Category> categoryList = courseService.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("myCourse", myCourse);
    }

    private void getMyCourseSuccess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int userId = ((User) currentSession.getAttribute("user")).getId();

        List<Course> myCourseSucess = courseService.getMyCourseSuccess(userId);
        List<Category> categoryList = courseService.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("myCourseSucess", myCourseSucess);
    }

    private void processRegisterCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        if (currentSession.getAttribute("user") == null) {
            // Process for non-user (register, send email then register course to account)
            processInputForRegister(request, response);
        } else {
            User currentUser = (User) currentSession.getAttribute("user");
            int courseId = 1;
            int pricePackageId = 1;

            try {
                courseId = Integer.parseInt(request.getParameter("courseId"));
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }

            try {
                pricePackageId = Integer.parseInt(request.getParameter("pricepkg"));
            } catch (Exception e) {
                System.out.println("Default package");
            }

            boolean isRegister = courseService.registerCourse(currentUser.getId(), courseId, pricePackageId);
            if (!isRegister) {
                response.sendRedirect(request.getContextPath() + "/nauth/down.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + request.getParameter("previousPage"));
            }
        }

    }

    private void processInputForRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("fullname");
        String mobile = request.getParameter("mobile");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        int courseId;
        int pricePackageId;

        try {
            courseId = Integer.parseInt(request.getParameter("courseId"));
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            return;
        }

        try {
            pricePackageId = Integer.parseInt(request.getParameter("pricepkg"));
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            return;
        }

        Account account = new Account(email, "abc123", Role.STUDENT);
        User user = new User(name, gender, email, Status.INACTIVE, mobile);

        processGuestCourseRegister(request, response, account, user, courseId, pricePackageId);
    }

    private void processGuestCourseRegister(HttpServletRequest request, HttpServletResponse response, Account account, User user, int courseId, int pricePackageId)
            throws ServletException, IOException {
        Account userAccount = authService.getAccount(account.getEmail());
        if (userAccount != null) {
            response.sendRedirect(request.getContextPath() + "/nauth/authenticate/register-failed.jsp");
        } else {
            String userToken = authService.register(account);
            if (userToken == null) {
                return;
            }

            boolean isUserAdded = userService.addUser(user);
            if (!isUserAdded) {
                return;
            }

            boolean isRegister = courseService.registerCourse(userService.getUser(account.getEmail()).getId(), courseId, pricePackageId);
            if (!isRegister) {
                response.sendRedirect(request.getContextPath() + "/nauth/down.jsp");
            } else {
                String createNewAccount = "/email?operation=CREATENEWACCOUNT&receiver=" + account.getEmail();
                request.getRequestDispatcher(createNewAccount).forward(request, response);
            }
        }
    }
}
