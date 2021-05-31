/**
 * 28/05/2021
 *
 * @author Hoang Tien Minh
 */
package auth;

import common.entities.Account;
import common.entities.User;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService() {
        authRepository = new AuthRepository();
    }

    // Login and get current user
    public User login(Account account) {
        try {
            return authRepository.login(account);
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

    public boolean activeAccount(String email, String token) {
        try {
            return authRepository.activeAccount(email, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
