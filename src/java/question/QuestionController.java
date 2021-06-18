/**
 * Jun 13, 1011
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Question;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionController extends HttpServlet {

    private QuestionService questionService;

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subjectId = request.getParameter("63");

        int subjectIndicator = 63;
//        HttpSession session = request.getSession();
//        session.setAttribute("sId", subjectId);
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);
        List<Question> questionList = questionService.getQuestions(63, indexPage);
        request.setAttribute("end", getTotalPage(request, response, subjectIndicator));
        request.setAttribute("tag", index);
        request.setAttribute("questionList", questionList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("SEARCHQUESTION")) {
            String subjectId = request.getParameter("63");
            int subjectIndicator = 63;
            String searchName = request.getParameter("searchQuestion");
            HttpSession ses = request.getSession();
            ses.setAttribute("searchName", searchName);
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);

            List<Question> questionList = questionService.searchQuestion(63, searchName, indexPage);

            request.setAttribute("end", getTotalPageSearch(request, response, 63, searchName));
            request.setAttribute("tag", index);
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }

    }

    private int getTotalPage(HttpServletRequest request, HttpServletResponse response, int courseId)
            throws ServletException, IOException {
        int total = questionService.countTotalQuestion(63);
        int endPage = 0;
        if (total % 5 == 0) {
            endPage = questionService.countTotalQuestion(63) / 5;
        } else {
            endPage = (questionService.countTotalQuestion(63) / 5) + 1;
        }
        return endPage;
    }

    private int getTotalPageSearch(HttpServletRequest request, HttpServletResponse response, int courseId, String searchName)
            throws ServletException, IOException {
        int total = questionService.countingQuestionListSearch(63, searchName);
        int endPage = 0;
        if (total % 5 == 0) {
            endPage = questionService.countingQuestionListSearch(63, searchName) / 8;
        } else {
            endPage = (questionService.countingQuestionListSearch(63, searchName) / 8) + 1;
        }
        return endPage;

    }
}
