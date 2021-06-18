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

}
