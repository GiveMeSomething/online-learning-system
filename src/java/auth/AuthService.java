/**
 * 28/05/2021
 *
 * @author Hoang Tien Minh
 */
package auth;

import common.entities.Account;
import java.sql.SQLException;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService() {
        authRepository = new AuthRepository();
    }

    // Login and return account token (if right)
    public String login(Account account) {
        try {
            return authRepository.login(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Account getAccount(String userEmail) {

        try {
            return authRepository.getAccount(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Register and receive authorization token
    public String register(Account account) {
        try {
            return authRepository.register(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getToken(String email, String password) {
        try {
            return authRepository.getToken(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean activeAccount(String email, String token) {
        try {
            return authRepository.activeAccount(email, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isValidToken(String email, String token) {
        try {
            return authRepository.isValidToken(email, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean changePassword(String userEmail, String password) {
        try {
            return authRepository.changePassword(userEmail, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkCurrentPass(String userEmail, String currentPassword) {
        try {
            return authRepository.checkCurrentPass(userEmail, currentPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getRoleIdOK(String email) {

        try {
            return authRepository.getRoleIdOK(email);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
