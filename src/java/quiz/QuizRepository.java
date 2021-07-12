/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.Lesson;
import common.entities.Level;
import common.entities.Question;
import common.entities.Quiz;
import common.entities.TestType;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizRepository extends Repository {

    public ArrayList<Quiz> getQuizList(int subjectId, String keyword, TestType quizType) throws SQLException {
        this.connectDatabase();

        String option = "";

        if (keyword != null && !keyword.equals("")) {
            option += "AND q.name LIKE '%" + keyword + "%' ";
        }

        if (quizType != null) {
            option += "AND q.quiz_type_id = " + TestType.valueOf(quizType) + " ";
        }

        String sql = "select q.id, "
                + "       q.name, "
                + "       q.subject_id, "
                + "       q.level_id, "
                + "       q.duration, "
                + "       q.pass_rate, "
                + "       q.quiz_type_id, "
                + "       q.description, "
                + "       c.title, "
                + "       COUNT(qq.quiz_id) as questNum "
                + "from quiz q "
                + "         inner join question_quiz qq on q.id = qq.quiz_id "
                + "         inner join course c on q.subject_id = c.id "
                + "where subject_id = ? " + option
                + "group by q.id, q.name, q.subject_id, q.level_id, q.duration, q.pass_rate, q.quiz_type_id, q.description, c.title;";
        ArrayList<Quiz> quizList = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, subjectId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");

                quizList.add(
                        new Quiz(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getInt("subject_id"),
                                result.getString("title"),
                                levelId == 1
                                        ? Level.EASY
                                        : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                                result.getInt("duration"),
                                result.getFloat("pass_rate"),
                                testType == 1
                                        ? TestType.SIMULATION
                                        : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                                result.getString("description"),
                                result.getInt("questNum")
                        )
                );
            }

            return quizList;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Question> getQuestion(int courseId, int dimensionId, int lessonId,
            int level, int numberOfQuestion) throws SQLException {
        this.connectDatabase();

        String sql = "";
        if (dimensionId == 0) {
            sql = "AND lesson_id = ?";
        } else if (lessonId == 0) {
            sql = "AND dimension_id = ?";
        }
        String getQuestion = "SELECT qcdl.question_id, option1, option2, option3, option4, option5, answer, explaination, content "
                + "FROM db_ite1.question_course_dim_les qcdl "
                + "JOIN questions_bank qb ON qcdl.question_id = qb.id "
                + "WHERE course_id = ? " + sql + " AND level_id = ? ORDER BY RAND() LIMIT ?";
        ArrayList<Question> questions = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(getQuestion)) {
            statement.setInt(1, courseId);
            if (dimensionId != 0) {
                statement.setInt(2, dimensionId);
            } else {
                statement.setInt(2, lessonId);
            }
            statement.setInt(3, level);
            statement.setInt(4, numberOfQuestion);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Question question = new Question();
                question.setId(result.getInt("question_id"));
                question.setOption1(result.getString("option1"));
                question.setOption2(result.getString("option2"));
                question.setOption3(result.getString("option3"));
                question.setOption4(result.getString("option4"));
                question.setOption5(result.getString("option5"));
                question.setAnswer(result.getString("answer"));
                question.setExplaination(result.getString("explaination"));
                question.setContent(result.getString("content"));

                questions.add(question);
            }

            return questions;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, ArrayList<Integer>> getDataForQuestion(int quizId) throws SQLException {
        this.connectDatabase();

        String getData = "SELECT * FROM quiz_dimension_lesson WHERE quiz_id = ?";
        HashMap<Integer, ArrayList<Integer>> getFullData = new HashMap<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getData)) {
            statement.setInt(1, quizId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ArrayList<Integer> getDataForQuestion = new ArrayList<>();
                getDataForQuestion.add(result.getInt("dimension_id"));
                getDataForQuestion.add(result.getInt("lesson_id"));
                getDataForQuestion.add(result.getInt("number_of_question"));
                getFullData.put(result.getInt("id"), getDataForQuestion);
            }
            return getFullData;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean getAnswerFromUser(int userQuizId, String userChoice,
            int quesitionId, boolean questionStatus) throws SQLException {
        this.connectDatabase();

        String getAnsFromUser = "INSERT INTO user_question(user_quiz_id, user_choice, question_id, question_status) "
                + "VALUES(?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(getAnsFromUser)) {
            statement.setInt(1, userQuizId);
            statement.setString(2, userChoice);
            statement.setInt(3, quesitionId);
            statement.setBoolean(4, questionStatus);

            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Dimension> getDimensionByType(Quiz quiz, int dimensionType) throws SQLException {
        this.connectDatabase();

        String dimensionByType = "SELECT DISTINCT qcdl.dimension_id, d.name "
                + "FROM db_ite1.question_course_dim_les qcdl JOIN dimension d "
                + "ON qcdl.dimension_id = d.id "
                + "JOIN questions_bank qb ON qb.id = qcdl.question_id "
                + "WHERE course_id = ? AND d.type_id = ? AND level_id = ?";
        ArrayList<Dimension> getHmDimesion = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(dimensionByType)) {
            statement.setInt(1, quiz.getSubjectId());
            statement.setInt(2, dimensionType);
            statement.setInt(3, Level.valueOf(quiz.getLevel()));

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Dimension dim = new Dimension();
                dim.setId(result.getInt("dimension_id"));
                dim.setName(result.getString("name"));
                getHmDimesion.add(dim);
            }
            return getHmDimesion;
        } finally {
            this.disconnectDatabase();
        }
    }

    // Not done yet
    public ArrayList<Lesson> getTopic(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String topic = "SELECT id, lesson_name as name FROM lesson "
                + "WHERE course_id = ? AND type_id = 1";
        ArrayList<Lesson> getTopic = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(topic)) {
            statement.setInt(1, quiz.getSubjectId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(result.getInt("id"));
                lesson.setName(result.getString("name"));
                getTopic.add(lesson);
            }
            return getTopic;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, String> getQuizForLesson(int courseId) throws SQLException {
        this.connectDatabase();

        String quizForLesson = "SELECT id, name FROM quiz WHERE subject_id = ?";
        HashMap<Integer, String> getQuizForLesson = new HashMap<>();
        try (PreparedStatement statement = this.connection.prepareStatement(quizForLesson)) {
            statement.setInt(1, courseId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                getQuizForLesson.put(result.getInt("id"), result.getString("name"));
            }
            return getQuizForLesson;
        } finally {
            this.disconnectDatabase();
        }
    }

    public DimensionType getQuizDimension(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "SELECT DISTINCT d.type_id FROM question_course_dim_les qcdl "
                + "JOIN question_quiz qq "
                + "ON qq.question_id = qcdl.question_id "
                + "JOIN dimension d "
                + "ON d.id = qcdl.dimension_id "
                + "WHERE quiz_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, quiz.getId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new DimensionType(result.getInt("type_id"),
                        result.getInt("type_id") == 2 ? "GROUP" : "DOMAIN");
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, String> getDimensionIDByQuizID(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "SELECT distinct dimension_id, d.name FROM question_quiz qq "
                + "JOIN question_course_dim_les qcdl "
                + "ON qq.question_id = qcdl.question_id "
                + "join dimension d "
                + "ON d.id = qcdl.dimension_id "
                + "WHERE quiz_id = ?";
        HashMap<Integer, String> getQuizDimesion = new HashMap<>();
        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, quiz.getId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                getQuizDimesion.put(result.getInt("dimension_id"),
                        result.getString("name"));
            }
            return getQuizDimesion;
        } finally {
            this.disconnectDatabase();
        }
    }

    public int countQuestionForEachDimension(Quiz quiz, int dimensionId) throws SQLException {
        this.connectDatabase();

        String questionCount = "SELECT count(qq.question_id) AS questionPerDim "
                + "FROM question_quiz qq JOIN question_course_dim_les qcdl "
                + "ON qq.question_id = qcdl.question_id "
                + "WHERE quiz_id = ? and dimension_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionCount)) {
            statement.setInt(1, quiz.getId());
            statement.setInt(2, dimensionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("questionPerDim");
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public ArrayList<Dimension> getDimensionTypeForEdit(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "select distinct type_id, d.name, dt.dimension_type_name "
                + "                from db_ite1.question_course_dim_les qcdl "
                + "                join question_quiz qq on qcdl.question_id = qq.question_id "
                + "                join dimension d on d.id = qcdl.dimension_id "
                + "                join dimension_type dt on dt.id = d.type_id "
                + "                where quiz_id = ?";
        ArrayList<Dimension> getQuizDimesion = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, quiz.getId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Dimension dim = new Dimension();
                dim.setId(result.getInt("type_id"));
                dim.setName(result.getString("name"));
                dim.setType(result.getString("dimension_type_name"));
                getQuizDimesion.add(dim);
            }
            return getQuizDimesion;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addQuizOverView(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String addQuizOverview = "INSERT INTO quiz(name, subject_id, level_id, "
                + "duration, pass_rate, quiz_type_id, description) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addQuizOverview)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, Level.valueOf(quiz.getLevel()));
            statement.setInt(4, quiz.getDuration());
            statement.setFloat(5, quiz.getPassRate());
            statement.setInt(6, TestType.valueOf(quiz.getQuizType()));
            statement.setString(7, quiz.getDescription());

            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addQuizSetting(Quiz quiz, int quesId) throws SQLException {
        this.connectDatabase();
        String addQuizSetting = "INSERT INTO question_quiz(question_id, quiz_id) values(?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addQuizSetting)) {
            statement.setInt(1, quesId);
            statement.setInt(2, quiz.getId());
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addNewQuizSetting(Quiz quiz, int dimensionId, int lessonId, int numberOfQuestion) throws SQLException {
        this.connectDatabase();

        String sql;
        if (lessonId == 0) {
            sql = "dimension_id";
        } else {
            sql = "lesson_id";
        }
        String addQuizSetting = "INSERT INTO quiz_dimension_lesson(quiz_id, " + sql + ", number_of_question)"
                + " VALUES(?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addQuizSetting)) {
            statement.setInt(1, quiz.getId());
            if (lessonId == 0) {
                statement.setInt(2, dimensionId);
            } else {
                statement.setInt(2, lessonId);
            }
            statement.setInt(3, numberOfQuestion);
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean updateQuizSetting(Quiz quiz, int dimensionId, int lessonId, int numberOfQuestion) throws SQLException {
        this.connectDatabase();

        String sql;
        if (lessonId == 0) {
            sql = "dimension_id";
        } else {
            sql = "lesson_id";
        }
        String addQuizSetting = "UPDATE quiz_dimension_lesson "
                + "SET " + sql + " = ?, number_of_question = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(addQuizSetting)) {
            if (lessonId == 0) {
                statement.setInt(1, dimensionId);
            } else {
                statement.setInt(1, lessonId);
            }
            statement.setInt(2, numberOfQuestion);
            statement.setInt(3, getQuizSetting(quiz, dimensionId, lessonId).get(0));
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Integer> getQuizSetting(Quiz quiz, int dimensionId, int lessonId) throws SQLException {
        this.connectDatabase();

        ArrayList<Integer> quizSetting = new ArrayList<>();
        String sql1;
        String sql2;
        if (lessonId == 0) {
            sql1 = "dimension_id";
            sql2 = "lesson_id";
        } else {
            sql1 = "lesson_id";
            sql2 = "dimension_id";
        }
        String getQuizSetting = "SELECT id, quiz_id, dimension_id, lesson_id, number_of_question "
                + "FROM quiz_dimension_lesson "
                + "WHERE quiz_id = ? AND " + sql1 + " = ? AND " + sql2 + " IS NULL";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuizSetting)) {
            statement.setInt(1, quiz.getId());
            if (lessonId == 0) {
                statement.setInt(2, dimensionId);
            } else {
                statement.setInt(2, lessonId);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                quizSetting.add(result.getInt("id"));
                quizSetting.add(result.getInt("quiz_id"));
                quizSetting.add(result.getInt("dimension_id"));
                quizSetting.add(result.getInt("lesson_id"));
                quizSetting.add(result.getInt("number_of_question"));
            }
            return quizSetting;
        }
    }

    public boolean updateQuizOverView(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String updateQuizOverview = "UPDATE quiz SET name= ?, subject_id= ?, "
                + "level_id = ?, duration = ?, pass_rate = ?, quiz_type_id = ?, "
                + "description = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateQuizOverview)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, Level.valueOf(quiz.getLevel()));
            statement.setInt(4, quiz.getDuration());
            statement.setFloat(5, quiz.getPassRate());
            statement.setInt(6, TestType.valueOf(quiz.getQuizType()));
            statement.setString(7, quiz.getDescription());
            statement.setInt(8, quiz.getId());
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Quiz getExistQuiz(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String getQuiz = "SELECT * FROM quiz q INNER JOIN course c on q.subject_id = c.id WHERE name = ? and q.subject_id = ? and q.quiz_type_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuiz)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, TestType.valueOf(quiz.getQuizType()));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");
                return new Quiz(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("subject_id"),
                        result.getString("title"),
                        levelId == 1
                                ? Level.EASY
                                : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                        result.getInt("duration"),
                        testType == 1
                                ? TestType.SIMULATION
                                : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                        result.getFloat("pass_rate"),
                        result.getString("description")
                );
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Quiz getQuiz(int id) throws SQLException {
        this.connectDatabase();

        String getQuiz = "SELECT * FROM quiz q INNER JOIN course c on q.subject_id = c.id WHERE q.id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuiz)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");
                return new Quiz(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("subject_id"),
                        result.getString("title"),
                        levelId == 1
                                ? Level.EASY
                                : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                        result.getInt("duration"),
                        testType == 1
                                ? TestType.SIMULATION
                                : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                        result.getFloat("pass_rate"),
                        result.getString("description")
                );
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public int countQuestion(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionCount = "SELECT COUNT(question_id) as question FROM question_quiz WHERE quiz_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionCount)) {
            statement.setInt(1, quiz.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("question");
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int getNumberOfQuestion(Quiz quiz, int dimensionId) throws SQLException {
        this.connectDatabase();

        String questionCount = "SELECT count(qq.question_id) as total_question FROM question_course_dim_les qcdl "
                + "JOIN question_quiz qq "
                + "ON qq.question_id = qcdl.question_id "
                + "WHERE quiz_id = ? and qcdl.dimension_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionCount)) {
            statement.setInt(1, quiz.getId());
            statement.setInt(2, dimensionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("total_question");
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int getQuizIdTheoYeuCauCuaDuyAnh(int lessonId) throws SQLException {
        this.connectDatabase();
        String questionCount = "SELECT quiz_id FROM db_ite1.lesson where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionCount)) {
            statement.setInt(1, lessonId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public boolean isFinishQuiz(String userId, String quizid) throws SQLException {
        this.connectDatabase();
        String questionCount = "SELECT * FROM db_ite1.user_quiz where user_id = ? and quiz_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(questionCount)) {
            statement.setString(1, userId);
            statement.setString(2, quizid);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getQuizReview(int quizId) throws SQLException {
        this.connectDatabase();

        String sql = "select qb.content, "
                + "       qb.option1, "
                + "       qb.option2, "
                + "       qb.option3, "
                + "       qb.option4, "
                + "       u.user_choice, "
                + "       qb.answer, "
                + "       IF(STRCMP(u.user_choice, qb.answer) = 0, 1, 0) result, "
                + "       qb.explaination "
                + "from user_quiz uq "
                + "         inner join user_question u on uq.id = u.user_quiz_id "
                + "         inner join questions_bank qb on u.question_id = qb.id "
                + "where uq.id = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);

            ArrayList<ArrayList<String>> questionList = new ArrayList<>();
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ArrayList<String> questionInfo = new ArrayList<>();
                questionInfo.add(result.getString("content"));
                questionInfo.add(result.getString("option1"));
                questionInfo.add(result.getString("option2"));
                questionInfo.add(result.getString("option3"));
                questionInfo.add(result.getString("option4"));
                questionInfo.add(result.getString("user_choice"));
                questionInfo.add(result.getString("answer"));
                questionInfo.add(result.getString("result"));
                questionInfo.add(result.getString("explaination"));

                questionList.add(questionInfo);
            }

            return questionList;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Object> getUserQuiz(int userId, int quizId) throws SQLException {
        this.connectDatabase();

        String getUserQuiz = "SELECT * FROM user_quiz WHERE user_id = ? AND quiz_id = ?";
        ArrayList<Object> userQuizId = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getUserQuiz)) {
            statement.setInt(1, userId);
            statement.setInt(2, quizId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userQuizId.add(result.getInt("id"));
                userQuizId.add(result.getInt("attempt"));
                userQuizId.add(result.getTimestamp("time_get_quiz"));
            }
            return userQuizId;
        }
    }

    public boolean addUserQuiz(int userId, int quizId) throws SQLException {
        this.connectDatabase();
        
        // Lay cai nay
        ArrayList<Object> userQuiz = getUserQuiz(userId, quizId);

        int attempt;
        if (userQuiz.isEmpty()) {
            attempt = 1;
        } else {
            attempt = (Integer) (userQuiz.get(userQuiz.size() - 2)) + 1;
        }
        String addUserQuiz = "INSERT INTO user_quiz(user_id, quiz_id, attempt) "
                + "VALUES(?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addUserQuiz)) {
            statement.setInt(1, userId);
            statement.setInt(2, quizId);
            statement.setInt(3, attempt);
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean checkExistQuestion(int quizId, int quesId) throws SQLException {
        this.connectDatabase();

        String checkQuestionQuiz = "SELECT * FROM question_quiz WHERE quiz_id = ? AND question_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(checkQuestionQuiz)) {
            statement.setInt(1, quizId);
            statement.setInt(2, quesId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt("quiz_id") != 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean addMark(float mark, int userQuizId) throws SQLException {
        this.connectDatabase();

        String checkQuestionQuiz = "UPDATE user_quiz SET mark = ? where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(checkQuestionQuiz)) {
            statement.setFloat(1, mark);
            statement.setInt(2, userQuizId);
            return statement.executeUpdate() > 0;
        }finally{
            this.disconnectDatabase();
        }
    }
}
