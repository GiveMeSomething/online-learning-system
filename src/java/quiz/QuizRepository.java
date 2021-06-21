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
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizRepository extends Repository {

    public boolean addQuizOverView(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String addQuizOverview = "INSERT INTO quiz(name, subject_id, level_id, "
                + "duration, pass_rate, quiz_type_id, description) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addQuizOverview)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, Level.valueOf(quiz.getQuizLevel()));
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

    public boolean updateQuizOverView(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String updateQuizOverview = "UPDATE quiz SET name= ?, subject_id= ?, "
                + "level_id = ?, duration = ?, pass_rate = ?, quiz_type_id = ?, "
                + "description = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateQuizOverview)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, Level.valueOf(quiz.getQuizLevel()));
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

    public boolean updateQuizSetting(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String updateQuizOverview = "UPDATE quiz SET name= ?, subject_id= ?, "
                + "level_id = ?, duration = ?, pass_rate = ?, quiz_type_id = ?, "
                + "description = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateQuizOverview)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, Level.valueOf(quiz.getQuizLevel()));
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

        String getQuiz = "SELECT * FROM quiz WHERE name = ? and subject_id = ? and quiz_type_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuiz)) {
            statement.setString(1, quiz.getQuizName());
            statement.setInt(2, quiz.getSubjectId());
            statement.setInt(3, TestType.valueOf(quiz.getQuizType()));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");
                return new Quiz(result.getInt("id"),
                        result.getString("name"),
                        result.getInt("subject_id"),
                        levelId == 1
                                ? Level.EASY
                                : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                        result.getInt("duration"),
                        result.getFloat("pass_rate"),
                        testType == 1
                                ? TestType.SIMULATION
                                : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                        result.getString("description"));
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Quiz getQuiz(int id) throws SQLException {
        this.connectDatabase();

        String getQuiz = "SELECT * FROM quiz WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuiz)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");
                return new Quiz(result.getInt("id"),
                        result.getString("name"),
                        result.getInt("subject_id"),
                        levelId == 1
                                ? Level.EASY
                                : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                        result.getInt("duration"),
                        result.getFloat("pass_rate"),
                        testType == 1
                                ? TestType.SIMULATION
                                : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                        result.getString("description"));
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

    public ArrayList<Quiz> getQuizList(int subjectId) throws SQLException {
        this.connectDatabase();

        String sql = "SELECT id, name, subject_id, level_id, duration, pass_rate, quiz_type_id, description "
                + "FROM quiz "
                + "WHERE subject_id = ?";
        ArrayList<Quiz> quizzes = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, subjectId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int levelId = result.getInt("level_id");
                int testType = result.getInt("quiz_type_id");

                quizzes.add(
                        new Quiz(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getInt("subject_id"),
                                levelId == 1
                                        ? Level.EASY
                                        : (levelId == 2 ? Level.MEDIUM : Level.HARD),
                                result.getInt("duration"),
                                result.getFloat("pass_rate"),
                                testType == 1
                                        ? TestType.SIMULATION
                                        : (testType == 2 ? TestType.TEST : TestType.QUIZ),
                                result.getString("description")
                        )
                );
            }

            return quizzes;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<Question> getQuestionByDimension(int courseId, int dimensionId, int numberOfQuestion) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "select qcdl.question_id "
                + "FROM db_ite1.question_course_dim_les qcdl "
                + "WHERE course_id = ? AND dimension_id = ? order by RAND() LIMIT ?;";
        ArrayList<Question> questions = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, courseId);
            statement.setInt(2, dimensionId);
            statement.setInt(3, numberOfQuestion);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                questions.add(new Question(result.getInt("question_id")));
            }

            return questions;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, String> getDimension(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "SELECT DISTINCT qcdl.dimension_id, d.name "
                + "FROM db_ite1.question_course_dim_les qcdl JOIN dimension d "
                + "ON qcdl.dimension_id = d.id WHERE course_id = ?;";
        HashMap<Integer, String> getHmDimesion = new HashMap<>();
        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, quiz.getSubjectId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                getHmDimesion.put(result.getInt("dimension_id"), result.getString("name"));
            }
            return getHmDimesion;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, String> getQuizDimension(Quiz quiz) throws SQLException {
        this.connectDatabase();

        String questionByLesson = "SELECT DISTINCT dimension_id FROM question_course_dim_les qcdl "
                + "JOIN question_quiz qq "
                + "ON qq.question_id = qcdl.question_id "
                + "WHERE quiz_id = ?";
        HashMap<Integer, String> getQuizDimesion = new HashMap<>();
        try (PreparedStatement statement = this.connection.prepareStatement(questionByLesson)) {
            statement.setInt(1, quiz.getId());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                getQuizDimesion.put(result.getInt("dimension_id"),
                        getDimension(quiz).get(result.getInt("dimension_id")));
            }
            return getQuizDimesion;
        } finally {
            this.disconnectDatabase();
        }
    }

    public static void main(String[] args) throws SQLException {
        QuizRepository quizRepository = new QuizRepository();
        Quiz quiz = new Quiz(1);
//        ArrayList<Question> getQues = quizRepository.getQuestionByDimension(3);
//        ArrayList<Question> getQues2 = quizRepository.getQuestionByDimension(4);

    }
}
