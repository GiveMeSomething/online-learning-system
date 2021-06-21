/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Level;
import common.entities.Quiz;
import common.entities.TestType;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

}
