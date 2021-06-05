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
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user) {
        try {
            return userRepository.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean activeUser(String email) {
        try {
            return userRepository.activeUser(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public User getUserProfile(int id) {
        try {
            return userRepository.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateUserProfile(User userUpdate) {
        try {
            return userRepository.updateUser(userUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
