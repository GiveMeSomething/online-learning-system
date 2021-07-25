/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import auth.AuthService;
import com.google.gson.Gson;
import common.entities.Account;
import common.entities.Course;
import common.entities.CourseRegistation;
import common.entities.Gender;
import common.entities.PricePackage;
import common.entities.Role;
import common.entities.Status;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import common.entities.User;
import common.utilities.Controller;
import course.CourseService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import user.UserService;

public class UserCourseController extends HttpServlet implements Controller {

    private UserCourseService userCourseService;
    private final int itemPerPage = 5;
    private AuthService authService;
    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        userCourseService = new UserCourseService();
        authService = new AuthService();
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        String operation = request.getParameter("operation");
        switch (operation) {
            case "SearchByCategory":
                searchCourseByCategory(request, response, u);
                break;
            case "SearchByTitle":
                searchCourseByTitle(request, response, u);
                break;
            case "UpdateStatus":
                updateStatus(request, response, u);
                break;
            case "VIEWALL":
                processViewRegistration(request, response);
                break;
            case "PAGINATION":
                processRegistrationPagination(request, response);
                break;
            case "VIEWDETAIL":
                processViewRegistrationDetail(request, response);
                break;
            case "GIFT":
                processAddRegistrationDetail(request, response);
                break;
            default:
                listCourseRegistation(request, response, u);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {

        } else {
            switch (operation) {
                case "EDITINFO":
                    processEditRegistrationInfo(request, response);
                    break;
                case "FILTER":
                    processFilterRegistration(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    private void processViewRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processFilterInput(request, response);
    }

    private void processFilterRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processFilterInput(request, response);
    }

    private void processRegistrationPagination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getItemInPage(request, response);
    }

    private void processViewRegistrationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userId"));
        int type;
        if (request.getParameter("type") == null || request.getParameter("type").equals("")) {
            type = 0;
        } else {
            type = Integer.parseInt(request.getParameter("type"));
        }
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        CourseRegistation regisDetail = userCourseService.getRegistrationDetail(userId, courseId);
        Course course = courseService.getCourse(courseId);
        ArrayList<PricePackage> coursePack = courseService.getCoursePackage(courseId);
        request.setAttribute("package", coursePack);
        request.setAttribute("course", course);
        request.setAttribute("detail", regisDetail);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/auth/user/registration/detail.jsp").forward(request, response);
    }

    private void processAddRegistrationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int type;
        if (request.getParameter("type") == null || request.getParameter("type").equals("")) {
            type = 0;
        } else {
            type = Integer.parseInt(request.getParameter("type"));
        }
        Course course = courseService.getCourse(courseId);
        ArrayList<PricePackage> coursePack = courseService.getCoursePackage(courseId);
        request.setAttribute("package", coursePack);
        request.setAttribute("course", course);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/auth/user/registration/detail.jsp").forward(request, response);
    }

    private void processEditRegistrationInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status;
        int courseId;
        HttpSession session = request.getSession();
        if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
        } else {
            status = 1;
        }
        String email = request.getParameter("email");
        User user = userService.getUser(email);
        if (request.getParameter("existCourseId") == null || request.getParameter("existCourseId").equals("")) {
            courseId = Integer.parseInt(request.getParameter("courseId"));
        } else {
            courseId = Integer.parseInt(request.getParameter("existCourseId"));
        }
        String name = request.getParameter("full-name");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String mess = "";
        User newUser;
        if (email == null) {
            newUser = userService.getUser(request.getParameter("existEmail"));
        } else {
            newUser = new User(user.getId(), "avatar_random.png",
                    name, Gender.valueOf(gender), email, user.getAddress(), mobile);
            boolean editUser = userService.updateUserProfile(newUser);
        }
        if (status == 2) {
            changeToPaidCourse(request, response, courseId, status, mess);
        } else if (status == 1) {
            notPaidYet(request, response, email, courseId, name, gender, mobile, status);
        } else {
            deleteRegistration(request, response);
        }

    }

    private void changeToPaidCourse(HttpServletRequest request, HttpServletResponse response,
            int courseId, int status, String mess)
            throws ServletException, IOException {
        //User does exist
        String email = request.getParameter("existEmail");
        int price = Integer.parseInt(request.getParameter("package"));
        if (authService.getAccount(email) == null) {
            // Create new account
            Account account = new Account(email, "abc123", Role.STUDENT);
            authService.register(account);

            // Get id from new account
            int newId = userService.getUser(email).getId();
            userCourseService.updateStatus(newId, courseId, status);
            String createNewAccount = "/email?operation=CREATENEWACCOUNT&receiver=" + email;
            request.getRequestDispatcher(createNewAccount).forward(request, response);
        } else {
            User user = userService.getUser(email);
            int userId = user.getId();
            boolean updateUser = userCourseService.updateRegistration(userId, courseId, price, request.getParameter("note"));
            boolean updateStatus = userCourseService.updateStatus(userId, courseId, status);
            if (updateUser && updateStatus) {
                mess = "Successfull";
            } else {
                mess = "Failed";
            }
            response.sendRedirect(request.getContextPath() + "/auth/teacher/registration?operation=VIEWALL&mess=" + mess);
        }
    }

    private void notPaidYet(HttpServletRequest request, HttpServletResponse response, String email,
            int courseId, String name, String gender, String mobile, int status)
            throws ServletException, IOException {
        int price = Integer.parseInt(request.getParameter("package"));
        int type = Integer.parseInt(request.getParameter("type"));
        String note = request.getParameter("note");
        if (type == 0) {
            if (userService.getUser(email) != null) {
                // User does exist
                User user = userService.getUser(email);
                int userId = user.getId();
                if (type == 0) {
                    userCourseService.addRegistrationForFriend(userId, courseId, price, note);
                } else {
                    userCourseService.updateRegistration(userId, courseId, price, note);
                }
            } else {
                User user = new User(name, Gender.valueOf(gender), email, Status.ACTIVE, mobile);
                userService.addUser(user);
                int userId = userService.getUser(email).getId();
                userCourseService.addRegistrationForFriend(userId, courseId, price, note);
            }
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            String existEmail = request.getParameter("existEmail");
            userCourseService.updateRegistration(userService.getUser(existEmail).getId(), courseId, price, note);
            response.sendRedirect(request.getContextPath() + "/auth/user/UserCourse?operation=");
        }
    }

    private void deleteRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mess = "";
        boolean updateStatus = userCourseService.updateStatus(Integer.parseInt(request.getParameter("userId")),
                Integer.parseInt(request.getParameter("courseId")), 0);
        if (updateStatus) {
            mess = "Successfull";
        } else {
            mess = "Failed";
        }
        response.sendRedirect(request.getContextPath() + "/auth/teacher/registration?operation=VIEWALL&mess=" + mess);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        int userId = u.getId();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int status = 0;
        userCourseService.updateStatus(userId, courseId, status);
        response.sendRedirect(request.getContextPath() + "/auth/user/UserCourse?operation=");
    }

    private void searchCourseByTitle(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        List<CourseRegistation> listC = userCourseService.searchCourseByTitle(u.getId(), title);
        for (CourseRegistation o : listC) {
            out.print("<tr>\n"
                    + "                                    <td>" + o.getId() + "</td>\n"
                    + "                                    <td>\n"
                    + "                                        <a href=\"#\">\n"
                    + "                                            " + o.getTitle() + "\n"
                    + "                                        </a>\n"
                    + "                                    </td>\n"
                    + "                                    <td>" + o.getRegistationTime() + "</td>  \n"
                    + "                                    <td>" + o.getTotalCost() + "</td>\n"
                    + "                                    <td>" + o.getValidFrom() + "</td>\n"
                    + "                                    <td>" + o.getValidTo() + "</td>");
            if (o.getStatus() == 0) {
                out.print("<td><span class=\"status text-danger\">&bull;</span> Cancelled</td>");
            }
            if (o.getStatus() == 1) {
                out.print("<td><span class=\"status text-warning\">&bull;</span> \n"
                        + "                                            <a href=\"UserCourse?operation=UpdateStatus&courseId=" + o.getId() + "\" class=\"confirmation\">Submited</a>\n"
                        + "                                        </td>");
            }
            if (o.getStatus() == 2) {
                out.print("<td><span class=\"status text-success\">&bull;</span> Completed</td>");
            }
            out.print("      <td>" + o.getPackages() + "</td>                        \n"
                    + "                                </tr>");
        }
    }

    private void searchCourseByCategory(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String categoryId = request.getParameter("category");
        List<CourseRegistation> listC = userCourseService.searchCourseByCategory(u.getId(), Integer.parseInt(categoryId));
        for (CourseRegistation o : listC) {
            out.print("<tr>\n"
                    + "                                    <td>" + o.getId() + "</td>\n"
                    + "                                    <td>\n"
                    + "                                        <a href=\"#\">\n"
                    + "                                            " + o.getTitle() + "\n"
                    + "                                        </a>\n"
                    + "                                    </td>\n"
                    + "                                    <td>" + o.getRegistationTime() + "</td>  \n"
                    + "                                    <td>" + o.getTotalCost() + "</td>\n"
                    + "                                    <td>" + o.getValidFrom() + "</td>\n"
                    + "                                    <td>" + o.getValidTo() + "</td>");
            if (o.getStatus() == 0) {
                out.print("<td><span class=\"status text-danger\">&bull;</span> Cancelled</td>");
            }
            if (o.getStatus() == 1) {
                out.print("<td><span class=\"status text-warning\">&bull;</span> \n"
                        + "                                            <a href=\"UserCourse?operation=UpdateStatus&courseId=" + o.getId() + "\" class=\"confirmation\">Submited</a>\n"
                        + "                                        </td>");
            }
            if (o.getStatus() == 2) {
                out.print("<td><span class=\"status text-success\">&bull;</span> Completed</td>");
            }
            out.print("      <td>" + o.getPackages() + "</td>                        \n"
                    + "                                </tr>");
        }
    }

    private void listCourseRegistation(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        int endPage = (userCourseService.countCourseUserRegis() / 5) + (userCourseService.countCourseUserRegis() % 5 == 0 ? 0 : 1);
        request.setAttribute("endPage", endPage);
        /*truyền cái u.getId() vào số 1 ở đây*/
        List<CourseRegistation> listC = userCourseService.getCourseUserRegister(u.getId(), Integer.parseInt(index));
        request.setAttribute("listC", listC);
        request.setAttribute("index", index);
        request.getRequestDispatcher("/auth/user/MyRegistation.jsp").forward(request, response);
    }

    private void processFilterInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        String keyword = request.getParameter("keyword");
        String fromString = request.getParameter("validFrom");
        String toString = request.getParameter("validTo");

        Date validFrom = null;
        Date validTo = null;

        String orderBy = request.getParameter("orderBy");

        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        if (orderBy == null || orderBy.equals("")) {
            orderBy = "";
        }

        // Change date input to Date object
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (fromString != null && !fromString.equals("")
                && toString != null && !toString.equals("")) {
            try {
                validFrom = formatter.parse(fromString);
                validTo = formatter.parse(toString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        currentSession.setAttribute("selectedKeyword", keyword);
        currentSession.setAttribute("selectedFrom", fromString);
        currentSession.setAttribute("selectedTo", toString);
        request.setAttribute("mess", request.getParameter("mess"));

        processGetAllRegistration(request, response, keyword, validFrom, validTo, orderBy);
    }

    private void processGetAllRegistration(HttpServletRequest request, HttpServletResponse response,
            String keyword, Date from, Date to, String orderBy)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int teacherId;
        if (currentSession.getAttribute("isAdmin") != null && (Boolean) currentSession.getAttribute("isAdmin")) {
            teacherId = -1;
        } else {
            teacherId = ((User) currentSession.getAttribute("user")).getId();
        }

        System.out.println(keyword);
        System.out.println(orderBy);
        System.out.println(teacherId);

        ArrayList<ArrayList<String>> registrationList = userCourseService.getCourseRegistations(keyword, from, to, orderBy, teacherId);
        currentSession.setAttribute("registrationList", registrationList);

        // Send data to the list page
        int page = processPageParameter(request, response, registrationList.size());
        ArrayList<ArrayList<String>> pageItems = getItemInPage(registrationList, page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/registration/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
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
            e.printStackTrace();
        }

        return page;
    }

    private ArrayList<ArrayList<String>> getItemInPage(ArrayList<ArrayList<String>> registrationList, int page) {
        if (page > ((registrationList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > registrationList.size() ? registrationList.size() : startItem + itemPerPage;

        ArrayList<ArrayList<String>> subjectInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            subjectInPage.add(registrationList.get(i));
        }

        return subjectInPage;
    }

    private void getItemInPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        ArrayList<ArrayList<String>> pageItems = getItemInPage((ArrayList<ArrayList<String>>) currentSession.getAttribute("registrationList"), page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/registration/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }
}
