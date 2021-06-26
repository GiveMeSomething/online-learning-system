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
    private int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        quizService = new QuizService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        String quizId;
        if (operation == null) {
            processInputForQuiz(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    // View quiz details
                    quizId = request.getParameter("quizId");
                    if (quizId == null || quizId.equals("")) {
                        Quiz quiz = null;
                        viewQuiz(request, response, quiz);
                    } else {
                        Quiz quiz = quizService.getQuiz(Integer.parseInt(quizId));
                        viewQuiz(request, response, quiz);
                    }
                    break;
                case "PAGINATION":
                    getItemInPage(request, response);
                    break;
                case "VIEWQUIZHANDLE":
                    processViewQuizHandle(request, response);
                    break;
                case "VIEWQUIZRESULT":
                    processQuizResult(request, response);
                    break;
                case "VIEWQUIZREVIEW":
                    processQuizReview(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("FILTER")) {
            processInputForQuiz(request, response);
        } else {
            switch (operation) {
                case "ADDQUIZSETTING":
                    HttpSession session = request.getSession();
                    Quiz quiz = (Quiz) session.getAttribute("quiz");
                    addQuizSetting(request, response, quiz);
                    break;
                case "ADDQUIZOVERVIEW":
                    String quizId = request.getParameter("quizId");
                    if (quizId == null || quizId.equalsIgnoreCase("")) {
                        addQuizOverview(request, response);
                    } else {
                        updateQuizOverview(request, response);
                    }
                    break;
                case "QUIZHANDLE":
                    processQuizHandle(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    private void processViewQuizHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processQuizHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processQuizResult(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processQuizReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        Quiz quiz = new Quiz(0, quizName, Integer.parseInt(subject), level, duration, type, passRate, description);
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
//        for (int i = 1; i <= 3; i++) {
//            int numberOfQuestions = Integer.parseInt(request.getParameter("number-of-question" + i));
//            int dimensionId = Integer.parseInt(request.getParameter("dimension-name" + i));
//            ArrayList<Question> questions = quizService.getQuestionByDimension(1, dimensionId, numberOfQuestions);
//            combination.addAll(questions);
//        }
        // Get value from Quiz Setting
        HttpSession session = request.getSession();
        int type = (Integer) session.getAttribute("type");
        String[] numberOfQues = request.getParameterValues("number-of-question");
        if (type == 1) {
            String[] lessonId = request.getParameterValues("dimension-name");
            int level = Level.valueOf(quiz.getLevel());
            for (int i = 0; i < numberOfQues.length; i++) {
                ArrayList<Question> questions = quizService.getQuestionByDimension(1, 0, Integer.parseInt(lessonId[i]),
                        level, Integer.parseInt(numberOfQues[i]));
                combination.addAll(questions);
            }
        } else {
            String[] dimensionId = request.getParameterValues("dimension-name");
            int level = Level.valueOf(quiz.getLevel());
            for (int i = 0; i < numberOfQues.length; i++) {
                ArrayList<Question> questions = quizService.getQuestionByDimension(1, Integer.parseInt(dimensionId[i]), 0,
                        level, Integer.parseInt(numberOfQues[i]));
                combination.addAll(questions);
            }
        }
        for (Question question : combination) {
            if (quizService.addQuizSetting(quiz, question.getId())) {
                // Do something
            }
        }
        session.setAttribute("quiz", null);
        response.sendRedirect(request.getContextPath() + "/auth/teacher/quiz");
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
        Quiz quiz = new Quiz(id, quizName, subject, level, duration, type, passRate, description);
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
        Quiz quiz = new Quiz(id, quizName, subject, level, duration, type, passRate, description);
        quizService.updateQuizOverView(quiz);
    }

    public void viewQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        HashMap<Integer, String> getHmCourse = courseService.getCourses();

//        HashMap<Integer, String> getDimension = quizService.getDimension(quiz);
        int countQuestion = quizService.countQuestion(quiz);
        request.setAttribute("course", getHmCourse);
        request.setAttribute("quiz", quiz);
        request.setAttribute("questionNumber", countQuestion);
//        request.setAttribute("dimension", getDimension);
        request.getRequestDispatcher("/auth/teacher/quiz/detail.jsp").forward(request, response);
    }
}
