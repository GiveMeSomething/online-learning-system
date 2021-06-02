/**
 * 28/05/2021
 *
 * @author Hoang Tien Minh
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

        try (PreparedStatement statement = this.connection.prepareStatement(getAccount)) {
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

            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    // Register new account and return token for user authorization
    public String register(Account account) throws SQLException {
        this.connectDatabase();

        Random randomer = new Random();

        // Check email and return null if the email has already existed
        String email = account.getEmail();
        if (isDuplicateAccount(email)) {
            return null;
        }

        int salt = randomer.nextInt(100);
        Role role = account.getRole();

        // Get hash password and token
        String password = HashPassword.getHashPassword(account.getPassword(), salt);
        String token = HashToken.getToken(email, salt, role.toString());

        String addAccount = "INSERT INTO account(user_email, password, role_id, salt, token) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addAccount)) {
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

    // Active user if get the right email and token
    public boolean activeAccount(String email, String token) throws SQLException {
        if (isValidToken(email, token)) {
            return userService.activeUser(email);
        }

        return false;
    }

    // Validate user token
    public boolean isValidToken(String email, String token) throws SQLException {
        this.connectDatabase();

        String checkToken = "SELECT * FROM account WHERE user_email=? AND token=?";
        try (PreparedStatement statement = this.connection.prepareStatement(checkToken)) {
            statement.setString(1, email);
            statement.setString(2, token);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    // Check if email already registered
    private boolean isDuplicateAccount(String email) throws SQLException {
        this.connectDatabase();

        String checkEmail = "SELECT * FROM account WHERE user_email=?";
        try (PreparedStatement statement = this.connection.prepareStatement(checkEmail)) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

            return false;
        }
    }
}
