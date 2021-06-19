/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Level;
import common.entities.Quiz;
import common.entities.TestType;
import course.CourseService;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizController extends HttpServlet {

    private QuizService quizService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        quizService = new QuizService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            String id = request.getParameter("id");
            if (id == null) {
                Quiz quiz = null;
                viewQuiz(request, response, quiz);
            } else {
                Quiz quiz = quizService.getQuiz(Integer.parseInt(id));
                viewQuiz(request, response, quiz);
            }
        } else if (operation.equals("ADDQUIZOVERVIEW")) {
            String id = request.getParameter("id");
            if (id == null) {
                addQuizOverview(request, response);
            } else {
                updateQuizOverview(request, response);
            }
        } else if (operation.equals("ADDQUIZSETTING")) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void addQuizOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizName = request.getParameter("quiz-name");
        String subject = request.getParameter("subject-name");
        Level level = Level.valueOf(request.getParameter("exam-level"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        float passRate = Float.parseFloat(request.getParameter("pass-rate"));
        TestType type = TestType.valueOf(request.getParameter("quiz-type"));
        String description = request.getParameter("description");
        Quiz quiz = new Quiz(0, quizName, Integer.parseInt(subject), level, duration, passRate, type, description);
        if (quizService.getExistQuiz(quiz) != null) {
            quizService.addQuizOverView(quiz);
        }
    }

    public void updateQuizOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String quizName = request.getParameter("quiz-name");
        int subject = Integer.parseInt(request.getParameter("subject-name"));
        Level level = Level.valueOf(request.getParameter("exam-level"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        float passRate = Float.parseFloat(request.getParameter("pass-rate"));
        TestType type = TestType.valueOf(request.getParameter("quiz-type"));
        String description = request.getParameter("description");
        Quiz quiz = new Quiz(id, quizName, subject, level, duration, passRate, type, description);
        quizService.updateQuizOverView(quiz);
    }

    public void viewQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        HashMap<Integer, String> getHmCourse = courseService.getCourses();
        request.setAttribute("course", getHmCourse);
        request.setAttribute("quiz", quiz);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }
}
