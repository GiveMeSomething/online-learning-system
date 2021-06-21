/**
 * Jun 13, 1011
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Level;
import common.entities.Question;
import common.entities.Status;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionController extends HttpServlet {

    private QuestionService questionService;
    private final int questionPerpage = 5;

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");
        int courseId = 2;
        if (operation == null) {
            processInputForQuestion(request, response);

        } else if (operation.equals("PAGINATION")) {
            int page = Integer.parseInt(request.getParameter("page"));

            List<Question> pageItems = getQuestionPerPage((List<Question>) session.getAttribute("questionList"), page);
            if (pageItems != null) {
                request.setAttribute("dimensionList", questionService.getDimensionList(courseId));
                request.setAttribute("pageItems", pageItems);
                request.getRequestDispatcher("/auth/teacher/subject/quiz/question/list.jsp").forward(request, response);
            } else {
                response.sendRedirect("/nauth/404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("SEARCHQUESTION")) {
            processInputForQuestion(request, response);
        }
    }

    public void processInputForQuestion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        String[] level = request.getParameterValues("level");
        String[] status = request.getParameterValues("status");
        String[] dimension = request.getParameterValues("dimension");
        List<Level> levelList;
        List<Status> statusList;
        List<String> dimensionList;

        if (level == null || level.length == 0) {
            levelList = null;
        } else {
            levelList = new ArrayList<>();
            for (int i = 0; i < level.length; i++) {
                levelList.add(Level.valueOf(level[i]));
            }
        }

        if (status == null || status.length == 0) {
            statusList = null;
        } else {
            statusList = new ArrayList<>();
            for (int i = 0; i < status.length; i++) {
                statusList.add(Status.valueOf(status[i]));
            }
        }

        if (dimension == null || dimension.length == 0) {
            dimensionList = null;
        } else {
            dimensionList = new ArrayList<>();
            for (int i = 0; i < dimension.length; i++) {
                dimensionList.add(dimension[i]);
            }
        }

        request.setAttribute("selectedDimension", dimensionList);
        request.setAttribute("selectedStatus", statusList);
        request.setAttribute("selectedLevel", levelList);
        request.setAttribute("selectedKeyword", keyword);

//        int courseId = Integer.parseInt(request.getParameter("thong nhat sau"));
        int courseId = 2;
        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        getQuestionList(request, response, courseId, keyword, levelList, statusList, dimensionList);
    }

    public void getQuestionList(HttpServletRequest request, HttpServletResponse response,
            int courseId, String keyword, List<Level> levels, List<Status> status, List<String> dimensionIds)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Question> questionList = questionService.getQuestionsWithCondition(courseId, keyword, levels, status, dimensionIds);
        session.setAttribute("questionList", questionList);

        int page;
        page = processPageParameter(request, response, questionList.size());
        List<Question> pageItems = getQuestionPerPage(questionList, page);

        if (pageItems != null) {
            request.setAttribute("dimensionList", questionService.getDimensionList(courseId));
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/subject/quiz/question/list.jsp").forward(request, response);
        } else {
            response.sendRedirect("/nauth/404.jsp");
        }
    }

    private List<Question> getQuestionPerPage(List<Question> questionList, int page) {
        if (page > ((questionList.size() / 5) + 1)) {
            return null;
        }
        int startQuestion = (page - 1) * questionPerpage;
        int endQuestion = (startQuestion + questionPerpage) > questionList.size() ? questionList.size() : startQuestion + questionPerpage;

        List<Question> questionInPage = new ArrayList<>();
        for (int i = startQuestion; i < endQuestion; i++) {
            questionInPage.add(questionList.get(i));
        }
        return questionInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1 || page > ((listSize / questionPerpage) + 1)) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 SubjectController");
        }

        return page;
    }
}
