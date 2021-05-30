/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package user;

import common.entities.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User getUser(String email) {
        try {
            return userRepository.getUser(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
