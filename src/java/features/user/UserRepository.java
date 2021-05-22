/**
 * 22/05/2021
 *
 * @author Hoang Tien Minh
 */
package features.user;

import database.DBContext;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.Repository;

public class UserRepository extends Repository {

    public User getUser(int id) throws Exception {
        this.connectDatabase();

        String sql = "SELECT u.id, u.image, u.fullname, u.gender, u.email, r.rolename, u.address, u.statusid, s.value, u.mobile "
                + "FROM user u INNER JOIN role r "
                + "ON u.roleid = r.id "
                + "INNER JOIN status s "
                + "ON u.statusid = s.id "
                + "WHERE u.id = ?";

        try (PreparedStatement statement = new DBContext().getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println("OK IN REPO");
                return new User(id,
                        result.getString("image"),
                        result.getString("fullname"),
                        result.getInt("gender") == 1 ? "male" : "female",
                        result.getString("email"),
                        result.getString("rolename"),
                        result.getString("address"),
                        result.getString("value"),
                        result.getString("mobile"));

            }
            System.out.println("NOT OK IN REPO");
            return null;
        }
    }
}
