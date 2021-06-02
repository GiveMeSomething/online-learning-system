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

        String getUser = "SELECT id, image, full_name, gender, email, address, status_id, mobile FROM user WHERE email=?";
        try (PreparedStatement statement = this.connection.prepareStatement(getUser)) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("full_name"),
                        result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                        result.getString("email"),
                        result.getString("address"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("mobile")
                );
            }

            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addUser(User user) throws SQLException {
        this.connectDatabase();

        // hard code ID until fix to auto increment id in DB
        String addUser = "INSERT INTO user (id, full_name, gender, email, status_id, mobile) "
                + "VALUES (11, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addUser)) {
            statement.setString(1, user.getName());
            statement.setInt(2, Gender.valueOf(user.getGender()));
            statement.setString(3, user.getEmail());
            statement.setInt(4, Status.valueOf(user.getStatus()));
            statement.setString(5, user.getMobile());

            if (statement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean activeUser(String email) throws SQLException {
        this.connectDatabase();

        String activeUser = "UPDATE user SET status_id=? WHERE email=?";
        try (PreparedStatement statement = this.connection.prepareStatement(activeUser)) {
            statement.setInt(1, Status.valueOf(Status.ACTIVE));
            statement.setString(2, email);

            boolean isUpdated = statement.executeUpdate() > 0;
            return isUpdated;
        } finally {
            this.disconnectDatabase();
        }
    }

}
