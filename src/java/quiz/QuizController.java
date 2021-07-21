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
    private int itemPerPage = 10;

    @Override
    public void init() throws ServletException {
        quizService = new QuizService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession currentSession = request.getSession();
        String operation = request.getParameter("operation");

        String quizId;
        if (operation == null) {
            boolean accessible = (Boolean) currentSession.getAttribute("isTeacher")
                    || (Boolean) currentSession.getAttribute("isAdmin");
            if (accessible) {
                processInputForQuiz(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/nauth/403.jsp");
            }
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
                case "dimensionType":
                    getDimensionByType(request, response);
                    break;
                case "dimension":
                    quizId = request.getParameter("quizId");
                    Quiz eQuiz = quizService.getQuiz(Integer.parseInt(quizId));
                    getDimensionByTypeForQuiz(request, response, eQuiz);
                    break;
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("operation");
        String quizId;
        if (operation.equals("FILTER")) {
            processInputForQuiz(request, response);
        } else {
            switch (operation) {
                case "ADDQUIZSETTING":
                    int type = Integer.parseInt(request.getParameter("type-control"));
                    if (type == 0) {
                        HttpSession session = request.getSession();
                        System.out.println("vao add quiz setting");
                        Quiz quiz = (Quiz) session.getAttribute("quiz");
                        addQuizSetting(request, response, quiz);
                    } else {
                        updateQuizSetting(request, response);
                    }
                    break;
                case "ADDQUIZOVERVIEW":
                    quizId = request.getParameter("quizId");
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int courseId = Integer.parseInt(session.getAttribute("courseId").toString());
        Quiz quiz = quizService.getQuiz(quizId);

        String page = request.getParameter("page");
        int pages = 1;
        if (page == null) {
            pages = 1;
        }
        int userQuizId = 0;
        ArrayList<Object> userQuiz = quizService.getUserQuiz(userId, quizId);
        if (userQuiz == null || userQuiz.isEmpty()) {
            // if userQuiz not exist, first time do this quiz
            quizService.addUserQuiz(userId, quizId);
            userQuiz = quizService.getUserQuiz(userId, quizId);
            userQuizId = (Integer) userQuiz.get(0);
            session.setAttribute("time", userQuiz.get(userQuiz.size() - 1));
        } else {
            // try to do this test over and over again
            quizService.addUserQuiz(userId, quizId); //add to store attempt in database
            userQuiz = quizService.getUserQuiz(userId, quizId);
            if (userQuiz.size() > 1) {
                userQuizId = (Integer) userQuiz.get(userQuiz.size() - 3);
                session.setAttribute("time", userQuiz.get(userQuiz.size() - 1));
            } else {
                userQuizId = (Integer) userQuiz.get(0);
                session.setAttribute("userQuizId", userQuizId);
            }
        }
        session.setAttribute("userQuizId", userQuizId);
        session.setAttribute("quizId", quizId);
        session.setAttribute("testTime", quiz.getDuration());
        HashMap<Integer, ArrayList<Integer>> getDataForQuestion = quizService.getDataForQuestion(quizId);
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
            if (!quizService.checkExistQuestion(quizId, question.getId())) {
                quizService.addQuizSetting(quiz, question.getId());
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        int pages = Integer.parseInt(page);
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
        HashMap<String, Boolean> markedQuestion = getMarkedQuestion(request, response, page);
        System.out.println("Answer from doQUizHandle" + session.getAttribute("answer"));
        if (page != pageFromProcess) {
            HashMap<String, String> userAnswer = getAnswer(request, response, page, pageFromProcess);
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

    private HashMap<String, String> getAnswer(HttpServletRequest request, HttpServletResponse response, int page, int pageFromProcess)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String answer = request.getParameter("q" + (page));
        HashMap<String, String> userAnswers = new HashMap<>();

        // all question save in answer session
        if (session.getAttribute("answer") != null && page != pageFromProcess) {
            userAnswers = (HashMap<String, String>) session.getAttribute("answer");
            userAnswers.put("" + page, answer);
        } else if (session.getAttribute("answer") == null && page != pageFromProcess) {
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
            // Day la loi cua view all question
            userAnswers.put("" + page, answer);
            System.out.println("get last ans khi ko null" + session.getAttribute("answer"));
        } else if (session.getAttribute("answer") == null) {
            userAnswers.put("" + page, answer);
            session.setAttribute("answer", userAnswers);
            System.out.println("get last ans khi null" + session.getAttribute("answer"));

        }
        return userAnswers;
    }

    //After Press Score button
    private void getUserAnswer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int page = Integer.parseInt(request.getParameter("page"));
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("question");
        System.out.println("size of ques in getuserAns " + questions.size());
        if (page == questions.size()) {
            session.setAttribute("marked", getMarkedQuestion(request, response, page));
            session.setAttribute("answer", getLastAnswer(request, response, page));
        }
        // get user_quiz_id from Quiz lesson
        int userQuizId = (Integer) session.getAttribute("userQuizId");
        System.out.println("userQuizId from getUserAnswer" + userQuizId);
        questions = (ArrayList<Question>) session.getAttribute("question");
        HashMap<String, String> userAnswer = (HashMap<String, String>) session.getAttribute("answer");
        HashMap<String, Boolean> questionStatus = (HashMap<String, Boolean>) session.getAttribute("marked");
        for (int i = 0; i < userAnswer.size(); i++) {
            String questionPage = (i + 1) + "";
            quizService.getAnswerFromUser(userQuizId, userAnswer.get(questionPage),
                    questions.get(i).getId(), questionStatus.get(questionPage));
        }
        checkUserAnswer(request, response, userQuizId);
    }

    private void checkUserAnswer(HttpServletRequest request, HttpServletResponse response, int userQuizId)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, String> userAnswer = (HashMap<String, String>) session.getAttribute("answer");
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("question");
        System.out.println("answer " + userAnswer);
        System.out.println("question " + questions);
        int countTrueAnswer = 0;
        for (int i = 0; i < questions.size(); i++) {
            int questionNumber = i + 1;
            if (questions.get(i).getAnswer().equalsIgnoreCase(userAnswer.get(questionNumber + ""))) {
                countTrueAnswer++;
            }
        }
        System.out.println(countTrueAnswer + " true ans");
        System.out.println(questions.size() + " question size");
        float score = (float) countTrueAnswer / questions.size();
        System.out.println(score);
        quizService.addMark(score, userQuizId);
        session.setAttribute("ketquacuoicung", score);
        session.setAttribute("answer", null);
        session.setAttribute("marked", null);
        session.setAttribute("question", null);
        String courseid = session.getAttribute("courseId").toString();
        String lessonId = session.getAttribute("lessonId").toString();
        response.sendRedirect(request.getContextPath() + "/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&lessonId=" + lessonId + "&courseId=" + courseid + "");

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
        System.out.println();
        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("user");

        int quizId = 1;
        int selectedQuestion = 0;

        try {
            quizId = Integer.parseInt(request.getParameter("quizId"));
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            return;
        }

        try {
            selectedQuestion = Integer.parseInt(request.getParameter("questionNum"));
        } catch (Exception e) {
            selectedQuestion = 0;
        }

        ArrayList<ArrayList<String>> questionList = quizService.getQuizReview(quizId, currentUser.getId());

        currentSession.setAttribute("questionList", questionList);
        request.setAttribute("quizId", quizId);
        request.setAttribute("questionNum", selectedQuestion);

        if (currentSession.getAttribute("previousPage") == null) {
            currentSession.setAttribute("previousPage", request.getHeader("referer"));
        }

        request.setAttribute("pageItem", questionList.get(selectedQuestion));
        request.getRequestDispatcher("/auth/user/quiz/quiz-review.jsp").forward(request, response);
    }

    private void processInputForQuiz(HttpServletRequest request, HttpServletResponse response, String message)
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
        request.setAttribute("errorMessage", message);
        // We don't need to process quizType because null is consider as quizType's default value
        processGetQuiz(request, response, keyword, quizType, subjectId);
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
            request.setAttribute("subjectId", subjectId);
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
            viewQuiz(request, response, thisQuiz);
        } else {
            this.forwardErrorMessage(request, response, "Already had this quiz", forwardTo);
        }
    }

    public void addQuizSetting(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        // Get value from Quiz Setting
        HttpSession session = request.getSession();
        String forwardTo = "/auth/teacher/quiz";
        String typeInString = request.getParameter("type");
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Quiz newQuiz = quizService.getQuiz(quizId);
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
            for (int i = 0; i < numberOfQues.length; i++) {
                quizService.addNewQuizSetting(newQuiz, 0, Integer.parseInt(lessonId[i]), Integer.parseInt(numberOfQues[i]));
            }
        } else {
            String[] dimensionId = request.getParameterValues("dimension-name");
            for (int i = 0; i < numberOfQues.length; i++) {
                if (numberOfQues[i] != null) {
                    quizService.addNewQuizSetting(newQuiz, Integer.parseInt(dimensionId[i]), 0, Integer.parseInt(numberOfQues[i]));
                }
            }
        }
        session.removeAttribute("quiz");
        viewQuiz(request, response, quiz, "Add quiz Successfully");
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
        if (quizService.updateQuizOverView(quiz)) {
            processInputForQuiz(request, response, "Update SuccessFully");
        }
    }

    public void updateQuizSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get value from Quiz Setting
        HttpSession session = request.getSession();
        String forwardTo = "/auth/teacher/quiz";
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        quizService.deleteQuizSetting(quizId);

        Quiz quiz = quizService.getQuiz(quizId);
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String typeInString = request.getParameter("type");
        int type;
        if (typeInString.equalsIgnoreCase("Group")) {
            type = 2;
        } else if (typeInString.equalsIgnoreCase("Lesson")) {
            type = 0;
        } else {
            type = 1;
        }

        HashMap<Integer, ArrayList<Integer>> hmData = quizService.getDataForQuestion(quizId);
        String[] numberOfQues = request.getParameterValues("number-of-question");
        if (numberOfQues == null || numberOfQues.equals("")) {
            session.setAttribute("quiz", null);
            this.forwardErrorMessage(request, response, "There is no question in this group", forwardTo);
        }
        if (type == 0) {
            String[] lessonId = request.getParameterValues("dimension-name");
            for (int i = 0; i < numberOfQues.length; i++) {
                quizService.addNewQuizSetting(quiz, 0, Integer.parseInt(lessonId[i]), Integer.parseInt(numberOfQues[i]));
            }
        } else {
            String[] dimensionId = request.getParameterValues("dimension-name");
            for (int i = 0; i < numberOfQues.length; i++) {
                if (numberOfQues[i] != null) {
                    quizService.addNewQuizSetting(quiz, Integer.parseInt(dimensionId[i]), 0, Integer.parseInt(numberOfQues[i]));
                }
            }
        }
        viewQuiz(request, response, quiz, "Update Successfully");
    }

    public void viewQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz, String message)
            throws ServletException, IOException {
        HashMap<Integer, Integer> questionPerDim = new HashMap<>();
        HashMap<Integer, String> getHmCourse = courseService.getCourses();
        HashMap<Integer, String> dimensionId = quizService.getDimensionIDByQuizID(quiz);
        int countQuestion = quizService.countQuestion(quiz);
        int courseId = Integer.parseInt(request.getParameter("subjectId"));
        request.setAttribute("course", getHmCourse);
        request.setAttribute("courseId", courseId);
        request.setAttribute("errorMessage", message);
        request.setAttribute("quiz", quiz);
        request.setAttribute("dimensionName", dimensionId);
        request.setAttribute("questionPerDimension", questionPerDim);
        request.setAttribute("questionNumber", countQuestion);
        request.getRequestDispatcher("/auth/teacher/quiz/detail.jsp").forward(request, response);
    }

    public void viewQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        HashMap<Integer, Integer> questionPerDim = new HashMap<>();
        int type = Integer.parseInt(request.getParameter("type"));
        HashMap<Integer, String> getHmCourse = courseService.getCourses();
        int countQuestion = quizService.countQuestion(quiz);
        int courseId = Integer.parseInt(request.getParameter("subjectId"));
        request.setAttribute("course", getHmCourse);
        request.setAttribute("courseId", courseId);
        request.setAttribute("quiz", quiz);
        request.setAttribute("type", type);
        request.setAttribute("questionPerDimension", questionPerDim);
        request.setAttribute("questionNumber", countQuestion);
        request.getRequestDispatcher("/auth/teacher/quiz/detail.jsp").forward(request, response);
    }

    public void getDimensionByType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typeInString = request.getParameter("type");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
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
            getTopic = quizService.getTopic(courseId);
            Gson json = new Gson();
            String topic = json.toJson(getTopic);
            response.setContentType("text/html");
            response.getWriter().write(topic);
        } else {
            getDimension = quizService.getDimension(courseId, type);
            Gson json = new Gson();
            String dimension = json.toJson(getDimension);
            response.setContentType("text/html");
            response.getWriter().write(dimension);
        }
    }

    public void getDimensionByTypeForQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        System.out.println(subjectId + " subjectId in dimension");
        HashMap<Integer, ArrayList<Integer>> dimension = quizService.getDataForQuestion(quiz.getId());
        HashMap<ArrayList<Integer>, ArrayList<String>> info = new HashMap<>();
        ArrayList<String> newInfo;
        Gson json = new Gson();
        for (Integer key : dimension.keySet()) {
            newInfo = quizService.getDimensionTypeForEdit(dimension.get(key).get(0), dimension.get(key).get(1));
            newInfo.add(dimension.get(key).get(2) + "");
            System.out.println(newInfo);
            info.put(dimension.get(key), newInfo);
        }
        String group = json.toJson(info);
        response.setContentType("text/html");
        response.getWriter().write(group);
    }
}
