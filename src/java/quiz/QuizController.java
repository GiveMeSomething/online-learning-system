/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Quiz;
import common.entities.TestType;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuizController extends HttpServlet {

    private QuizService quizService;
    private final int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        quizService = new QuizService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            processInputForQuiz(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    // View quiz details
                    break;
                case "PAGINATION":
                    getItemInPage(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("FILTER")) {
            processInputForQuiz(request, response);
        }
    }

    private void processInputForQuiz(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String typeString = request.getParameter("quizType");
        TestType quizType;

        int subjectId = 1;

        if (typeString != null && !typeString.equals("")) {
            quizType = TestType.valueOf(typeString);
        } else {
            quizType = null;
        }

        // If there is no subjectId, change subject to default value (1)
        try {
            subjectId = Integer.parseInt(request.getParameter("subjectId"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~70 in LessonController");
        }

        // Save selected value to request
        request.setAttribute("selectedType", typeString);
        request.setAttribute("selectedKeyword", keyword);

        // We don't need to process quizType because null is consider as quizType's default value
        processGetQuiz(request, response, keyword, quizType, subjectId);
    }

    private void processGetQuiz(HttpServletRequest request, HttpServletResponse response,
            String keyword, TestType quizType, int subjectId)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Save the base array into current session
        ArrayList<Quiz> quizList = this.quizService.getQuizList(subjectId, keyword, quizType);
        currentSession.setAttribute("quizList", quizList);

        // Send data to the list page
        System.out.println(quizList);
        int page = this.processPageParameter(request, response, quizList.size());
        ArrayList<Quiz> pageItems = this.getItemInPage(quizList, page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/quiz/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    private void getItemInPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        ArrayList<Quiz> pageItems = getItemInPage((ArrayList<Quiz>) currentSession.getAttribute("quizList"), page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/quiz/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    private ArrayList<Quiz> getItemInPage(ArrayList<Quiz> quizList, int page) {
        if (page > ((quizList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > quizList.size() ? quizList.size() : startItem + itemPerPage;

        ArrayList<Quiz> quizInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            quizInPage.add(quizList.get(i));
        }

        return quizInPage;
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
}
