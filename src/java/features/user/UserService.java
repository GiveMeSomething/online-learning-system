/**
 * 22/05/2021
 *
 * @author Hoang Tien Minh
 */
package features.user;

import entities.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getUser(int id) {
        try {
            User result = userRepository.getUser(id);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
