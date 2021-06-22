/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionRepository extends Repository {

    public boolean addQuestion(int status, String content, String media, String explaination, String answer, String option1,
            String option2, String option3, String option4) throws SQLException {
        this.connectDatabase();
        String query = "INSERT INTO `db_ite1`.`questions_bank` (`status_id`, `content`, `media`, `explaination`, `answer`, `option1`, `option2`, `option3`, `option4`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, status);
            statement.setString(2, content);
            statement.setString(3, media);
            statement.setString(4, explaination);
            statement.setString(5, answer);
            statement.setString(6, option1);
            statement.setString(7, option2);
            statement.setString(8, option3);
            statement.setString(9, option4);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }
}
