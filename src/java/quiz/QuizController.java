/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Level;
import common.entities.Question;
import common.entities.Quiz;
import common.entities.TestType;
import common.utilities.Controller;
import course.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuizController extends HttpServlet implements Controller {

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
        int courseId = 1;
        if (operation == null) {
            String id = request.getParameter("id");
            if (id == null || id.equals("")) {
                Quiz quiz = null;
                viewQuiz(request, response, quiz);
            } else {
                Quiz quiz = quizService.getQuiz(Integer.parseInt(id));
                viewQuiz(request, response, quiz);
            }
        } else if (operation.equals("ADDQUIZOVERVIEW")) {
            String id = request.getParameter("id");
            if (id.equalsIgnoreCase("")) {
                addQuizOverview(request, response);
            } else {
                updateQuizOverview(request, response);
            }
        } else if (operation.equals("ADDQUIZSETTING")) {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            Quiz quiz = (Quiz)session.getAttribute("quiz");
            addQuizSetting(request, response, quiz);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void addQuizOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String forwardTo = request.getParameter("previousPage");
        String quizName = request.getParameter("quiz-name");
        String subject = request.getParameter("subject-name");
        Level level = Level.valueOf(request.getParameter("exam-level"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        float passRate = Float.parseFloat(request.getParameter("pass-rate"));
        TestType type = TestType.valueOf(request.getParameter("quiz-type"));
        String description = request.getParameter("description");
        Quiz quiz = new Quiz(0, quizName, Integer.parseInt(subject), level, duration, passRate, type, description);
        if (quizService.getExistQuiz(quiz) == null) {
            quizService.addQuizOverView(quiz);
            Quiz thisQuiz = quizService.getExistQuiz(quiz);
            session.setAttribute("quiz", thisQuiz);
            viewQuiz(request, response, thisQuiz);
        } else {
            this.forwardErrorMessage(request, response, "Already had this quiz", forwardTo);
        }
    }

    public void addQuizSetting(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        ArrayList<Question> combination = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int numberOfQuestions = Integer.parseInt(request.getParameter("number-of-question" + i));
            int dimensionId = Integer.parseInt(request.getParameter("dimension-name" + i));
            ArrayList<Question> questions = quizService.getQuestionByDimension(1, dimensionId, numberOfQuestions);
            combination.addAll(questions);
        }
        for (Question question : combination) {
            if (quizService.addQuizSetting(quiz, question.getId())) {
            }
        }
    }

    public void updateQuizOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

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
        
        // After updated, set quiz in session
        session.setAttribute("quiz", quiz);
        viewQuiz(request, response, quiz);
    }

    public void updateQuizSetting(HttpServletRequest request, HttpServletResponse response)
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
        HashMap<Integer, String> getDimension = quizService.getDimension(quiz);
        int countQuestion = quizService.countQuestion(quiz);
        request.setAttribute("course", getHmCourse);
        request.setAttribute("quiz", quiz);
        request.setAttribute("questionNumber", countQuestion);
        request.setAttribute("dimension", getDimension);
        request.getRequestDispatcher("/auth/teacher/subject/quiz/detail.jsp").forward(request, response);
    }
}
