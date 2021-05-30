/**
 * 28/05/2021
 *
 * @author Admin
 */
package auth;

import common.entities.Account;
import common.entities.Role;
import common.entities.User;
import common.utilities.HashPassword;
import common.utilities.HashToken;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import user.UserService;

public class AuthRepository extends Repository {

    private final UserService userService;

    public AuthRepository() {
        userService = new UserService();
    }

    public User login(Account account) throws SQLException {
        this.connectDatabase();

        String email = account.getEmail();
        String inputPassword = account.getPassword();

        String getAccount = "SELECT user_email, password FROM account WHERE user_email=?";

        try (PreparedStatement statement = connection.prepareStatement(getAccount)) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            // If there is a user of that email, proceed to check password
            if (result.next()) {
                String password = result.getString("password");
                String salt = result.getString("salt");

                // Check password then return user
                if (HashPassword.validatePassword(password, salt, inputPassword)) {
                    return userService.getUser(email);
                }
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    // Register new account and return token for user authorization
    public String register(Account account) throws SQLException {
        this.connectDatabase();
        Random randomer = new Random();

        String email = account.getEmail();
        int salt = randomer.nextInt(100);
        Role role = account.getRole();

        // Get hash password and token
        String password = HashPassword.getHashPassword(account.getPassword(), salt);
        String token = HashToken.getToken(email, salt, role.toString());

        String addAccount = "INSERT INTO account(user_email, password, role_id, salt, token) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addAccount)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, Role.valueOf(role));
            statement.setInt(4, salt);
            statement.setString(5, token);

            // Run if the account is added to the database
            if (statement.executeUpdate() > 0) {
                return token;
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean isDuplicateUser(String email) {
        return false;
    }

}
