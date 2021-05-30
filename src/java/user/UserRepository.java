/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package user;

import common.entities.Gender;
import common.entities.Status;
import common.entities.User;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    public User getUser(String email) throws SQLException {
        this.connectDatabase();

        String getUser = "SELECT id, image, full_name, gender, email, address, status_id, mobile WHERE user_email=?";
        try (PreparedStatement statement = connection.prepareStatement(getUser)) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("full_name"),
                        result.getInt("gender") == 1 ? Gender.Male : Gender.Female,
                        result.getString("email"),
                        result.getString("address"),
                        result.getInt("status") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("mobile")
                );
            }
        } finally {
            this.disconnectDatabase();
        }

        return null;
    }
}
