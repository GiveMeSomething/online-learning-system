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
import com.google.gson.Gson;
import common.entities.Dimension;
import common.entities.Lesson;
import common.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
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
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");

        String quizId;
        System.out.println(operation);
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
                        session.setAttribute("quiz", quiz);
                        viewQuiz(request, response, quiz);
                    }
                    break;
                case "PAGINATION":
                    getItemInPage(request, response);
                    break;
                case "dimensionType":
                    Quiz quiz = (Quiz) session.getAttribute("quiz");
                    getDimensionByType(request, response, quiz);
                    break;
                case "dimensionName":
                    Quiz existQuiz = (Quiz) session.getAttribute("quiz");
                    getDimensionNameForQuiz(request, response, existQuiz);
                    break;
                case "dimension":
                    quizId = request.getParameter("quizId");
                    Quiz eQuiz = quizService.getQuiz(Integer.parseInt(quizId));
                    getDimensionByTypeForQuiz(request, response, eQuiz);
                    break;
                ///auth/user/quiz?operation=VIEWQUIZHANDLE
                case "VIEWQUIZHANDLE":
                    processViewQuizHandle(request, response);
                    break;
                case "QUIZHANDLE":
                    processQuizHandle(request, response);
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
                case "SUBMITQUIZ":
                    getUserAnswer(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    private void processViewQuizHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();

//        int quizId = 14;
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int courseId = Integer.parseInt(session.getAttribute("courseId").toString());

        String page = request.getParameter("page");
        int pages = 1;
        if (page == null) {
            pages = 1;
        }

        ArrayList<Integer> userQuiz = quizService.getUserQuiz(userId, quizId);
        if (userQuiz.isEmpty()) {
            quizService.addUserQuiz(userId, quizId);
        }
        int userQuizId;
        if (userQuiz.size() > 1) {
            userQuizId = userQuiz.get(userQuiz.size() - 2);
        } else {
            userQuizId = userQuiz.get(0);
        }

        session.setAttribute("userQuizId", userQuizId); 

        HashMap<Integer, ArrayList<Integer>> getDataForQuestion = quizService.getDataForQuestion(1);
        Quiz quiz = quizService.getQuiz(1);
        ArrayList<Question> questions;
        ArrayList<Question> combination = new ArrayList<>();

        for (Integer key : getDataForQuestion.keySet()) {
            questions = quizService.getQuestionByDimension(courseId, getDataForQuestion.get(key).get(0),
                    getDataForQuestion.get(key).get(1), Level.valueOf(quiz.getLevel()), getDataForQuestion.get(key).get(2));
            combination.addAll(questions);
        }
        if (session.getAttribute("question") == null) {
            session.setAttribute("question", combination);
        }

        ArrayList<Question> questionInSession = (ArrayList<Question>) session.getAttribute("question");
        for (Question question : questionInSession) {
            if (quizService.addQuizSetting(quiz, question.getId())) {
                // Do something
            }
        }

        int startItem = (pages - 1) * 1;
        int endItem = (startItem + 1) > questionInSession.size() ? questionInSession.size() : startItem + 1;

        ArrayList<Question> quesInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            quesInPage.add(questionInSession.get(i));
        }

        request.setAttribute("question", quesInPage);
        request.setAttribute("questionSize", questionInSession.size());
        request.getRequestDispatcher("/auth/user/quiz/quiz-handler.jsp").forward(request, response);
    }

    private void processQuizHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        int pages = Integer.parseInt(page);
        System.out.println("day la pages in processQUizHandle " + pages);
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("question");

        int startItem = (pages - 1) * 1;
        int endItem = (startItem + 1) > questions.size() ? questions.size() : startItem + 1;
        doQuizHandle(request, response);
        ArrayList<Question> quesInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            quesInPage.add(questions.get(i));
        }
        request.setAttribute("question", quesInPage);
        request.setAttribute("questionSize", questions.size());
        request.getRequestDispatcher("/auth/user/quiz/quiz-handler.jsp").forward(request, response);
    }

    private void doQuizHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int page = Integer.parseInt(request.getParameter("thisPage"));
        int pageFromProcess = Integer.parseInt(request.getParameter("page"));
        System.out.println("Day la page from doQuizHandle " + page);

        HashMap<String, Boolean> markedQuestion = getMarkedQuestion(request, response, page);
        if (page != pageFromProcess) {
            HashMap<String, String> userAnswer = getAnswer(request, response, page);
        } else {
            HashMap<String, String> userAnswer = getLastAnswer(request, response, page);
        }
    }

    private HashMap<String, Boolean> getMarkedQuestion(HttpServletRequest request, HttpServletResponse response, int page)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean mark = request.getParameter("mark") != null;
        System.out.println("This is getMarked " + mark);
        HashMap<String, Boolean> marked = new HashMap<>();

        // getAttribute("marked") saves marked status
        if (session.getAttribute("marked") == null) {
            marked.put(page + "", mark);
            session.setAttribute("marked", marked);
        } else if (session.getAttribute("marked") != null) {
            marked = (HashMap<String, Boolean>) session.getAttribute("marked");
            marked.put(page + "", mark);
        }
        return marked;
    }

    private HashMap<String, String> getAnswer(HttpServletRequest request, HttpServletResponse response, int page)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String answer = request.getParameter("q" + (page));
        HashMap<String, String> userAnswers = new HashMap<>();

        // all question save in answer session
        if (session.getAttribute("answer") != null) {
            userAnswers = (HashMap<String, String>) session.getAttribute("answer");
            userAnswers.put("" + page, answer);
        } else if (session.getAttribute("answer") == null) {
            userAnswers.put("" + page, answer);
            session.setAttribute("answer", userAnswers);

        }
        return userAnswers;
    }


    private HashMap<String, String> getLastAnswer(HttpServletRequest request, HttpServletResponse response, int page)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String answer = request.getParameter("q" + (page));
        HashMap<String, String> userAnswers = new HashMap<>();

        // all question save in answer session
        if (session.getAttribute("answer") != null) {
            userAnswers = (HashMap<String, String>) session.getAttribute("answer");
            userAnswers.put("" + page, answer);
        } else if (session.getAttribute("answer") == null) {
            userAnswers.put("" + page, answer);
            session.setAttribute("answer", userAnswers);
        }
        return userAnswers;
    }

    //After Press Score button
    private void getUserAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int page = Integer.parseInt(request.getParameter("page"));
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("question");
        if (page == questions.size()) {
            session.setAttribute("marked", getMarkedQuestion(request, response, page));
            session.setAttribute("answer", getLastAnswer(request, response, page));
        }
        // get user_quiz_id from Quiz lesson
        int userQuizId = (Integer) session.getAttribute("userQuizId");
        questions = (ArrayList<Question>) session.getAttribute("question");
        HashMap<String, String> userAnswer = (HashMap<String, String>) session.getAttribute("answer");
        System.out.println("answer " + userAnswer);
        System.out.println("question " + questions);
        HashMap<String, Boolean> questionStatus = (HashMap<String, Boolean>) session.getAttribute("marked");
        for (int i = 0; i < questions.size(); i++) {
            String questionPage = (i + 1) + "";
            quizService.getAnswerFromUser(userQuizId, userAnswer.get(questionPage),
                    questions.get(i).getId(), questionStatus.get(questionPage));
        }
        checkUserAnswer(request, response);
    }

    private void checkUserAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, String> userAnswer = (HashMap<String, String>) session.getAttribute("answer");
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("question");
        int countTrueAnswer = 0;
        for (int i = 0; i < questions.size(); i++) {
            int questionNumber = i + 1;
            if (questions.get(i).getAnswer().equalsIgnoreCase(userAnswer.get(questionNumber + ""))) {
                countTrueAnswer++;
            }
        }
        float score = (float) countTrueAnswer / questions.size();

        session.setAttribute("ketquacuoicung", score);

        //Lay diem o day
        System.out.println(score);
        request.getRequestDispatcher("/auth/user/quiz/quiz-lesson.jsp").forward(request, response);

    }

    private void processQuizResult(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
//        int quizId = quizService.getQuizId(Integer.parseInt(session.getAttribute("courseId").toString()));
//        request.setAttribute("quizId", quizId);
//        request.setAttribute("ketquacuoicung", session.getAttribute("ketquacuoicung").toString());
//        request.getRequestDispatcher("/auth/user/quiz/quiz-lesson.jsp").forward(request, response);
        User u = (User) session.getAttribute("user");
        out.print(u.getId());   
        request.setAttribute("ketquacuoicung", session.getAttribute("ketquacuoicung").toString());
        request.getRequestDispatcher("/auth/user/quiz/quiz-lesson.jsp").forward(request, response);
    }

    private void processQuizReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int quizId = 1;
        int selectedQuestion = 0;

        try {
            quizId = Integer.parseInt(request.getParameter("userQuizId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            selectedQuestion = Integer.parseInt(request.getParameter("questionNum"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> questionList = (ArrayList<ArrayList<String>>) currentSession.getAttribute("questionList");
        if (questionList == null) {
            questionList = quizService.getQuizReview(quizId);
        }

        currentSession.setAttribute("questionList", questionList);
        request.setAttribute("questionNum", selectedQuestion);

        request.setAttribute("pageItem", questionList.get(selectedQuestion));
        request.getRequestDispatcher("/auth/user/quiz/quiz-review.jsp").forward(request, response);
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
            System.out.println(e.getMessage() + " at ~70 in QuizController");
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
            System.out.println(e.getMessage() + " at ~96 QuizController");
        }

        return page;
    }

    public void addQuizOverview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String forwardTo = "/auth/teacher/quiz";
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
        // Get value from Quiz Setting
        HttpSession session = request.getSession();
        String forwardTo = "/auth/teacher/quiz";

//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int courseId = 1;
        String typeInString = request.getParameter("type");
        int type;
        if (typeInString.equalsIgnoreCase("Group")) {
            type = 2;
        } else if (typeInString.equalsIgnoreCase("Lesson")) {
            type = 0;
        } else {
            type = 1;
        }
        String[] numberOfQues = request.getParameterValues("number-of-question");
        if (numberOfQues == null || numberOfQues.equals("")) {
            session.setAttribute("quiz", null);
            this.forwardErrorMessage(request, response, "There is no question in this group", forwardTo);
        }
        if (type == 0) {
            String[] lessonId = request.getParameterValues("dimension-name");
            int level = Level.valueOf(quiz.getLevel());
            for (int i = 0; i < numberOfQues.length; i++) {
                ArrayList<Question> questions = quizService.getQuestionByDimension(courseId, 0, Integer.parseInt(lessonId[i]),
                        level, Integer.parseInt(numberOfQues[i]));
                if (questions != null) {
                    combination.addAll(questions);
                }
            }
        } else {
            String[] dimensionId = request.getParameterValues("dimension-name");
            int level = Level.valueOf(quiz.getLevel());
            for (int i = 0; i < numberOfQues.length; i++) {
                if (numberOfQues[i] != null) {
                    ArrayList<Question> questions = quizService.getQuestionByDimension(courseId, Integer.parseInt(dimensionId[i]), 0,
                            level, Integer.parseInt(numberOfQues[i]));
                    if (questions != null) {
                        combination.addAll(questions);
                    }
                }
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

        int id = Integer.parseInt(request.getParameter("quizId"));
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

    // Not done yet
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
        HashMap<Integer, Integer> questionPerDim = new HashMap<>();
        HashMap<Integer, String> getHmCourse = courseService.getCourses();
        HashMap<Integer, String> dimensionId = quizService.getDimensionIDByQuizID(quiz);
        int countQuestion = quizService.countQuestion(quiz);
        request.setAttribute("course", getHmCourse);
        request.setAttribute("quiz", quiz);
        request.setAttribute("dimensionName", dimensionId);
        request.setAttribute("questionPerDimension", questionPerDim);
        request.setAttribute("questionNumber", countQuestion);
        request.getRequestDispatcher("/auth/teacher/quiz/detail.jsp").forward(request, response);
    }

    public void getDimensionByType(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        String typeInString = request.getParameter("type");
        int type = 0;
        if (typeInString.equalsIgnoreCase("Group")) {
            type = 2;
        } else if (typeInString.equalsIgnoreCase("Lesson")) {
            type = 0;
        } else {
            type = 1;
        }
        ArrayList<Dimension> getDimension;
        ArrayList<Lesson> getTopic;
        if (type == 0) {
            getTopic = quizService.getTopic(quiz);
            Gson json = new Gson();
            String topic = json.toJson(getTopic);
            response.setContentType("text/html");
            response.getWriter().write(topic);
        } else {
            getDimension = quizService.getDimension(quiz, type);
            Gson json = new Gson();
            String dimension = json.toJson(getDimension);
            response.setContentType("text/html");
            response.getWriter().write(dimension);
        }
    }

    public void getDimensionByTypeForQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        ArrayList<Dimension> dimension = quizService.getDimensionTypeForEdit(quiz);
        System.out.println(dimension);
        Gson json = new Gson();
        String group = json.toJson(dimension);
        response.setContentType("text/html");
        response.getWriter().write(group);
    }

    public void getDimensionNameForQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        ArrayList<Dimension> dimension = quizService.getDimensionTypeForEdit(quiz);
        System.out.println(dimension);
        Gson json = new Gson();
        String group = json.toJson(dimension);
        response.setContentType("text/html");
        response.getWriter().write(group);
    }
}
