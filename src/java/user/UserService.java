/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package user;

import common.entities.User;
import java.util.List;

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

    public List<User> pagingUser(int index) {
        List<User> list;
        try {
            list = this.userRepository.pagingUser(index);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> searchUser(String txt) {
        List<User> list;
        try {
            list = this.userRepository.searchUser(txt);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> searchUserByField(String searchUserByField, int index) {
        List<User> list;
        try {
            list = this.userRepository.searchUserByField(searchUserByField, index);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int countSearchUserByField(String countSearchUserByField) {

        try {
            return userRepository.countSearchUserByField(countSearchUserByField);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public User getUserById(int id) {
        try {
            return userRepository.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateUserInformation(String img, int id, String fullname, boolean gender, String address, String mobile) {
        try {
            return userRepository.updateUserInformation(img, id, fullname, gender, address, mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertAccount(String account, String password, int roleId) {
        try {
            return userRepository.insertAccount(account, password, roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateRole(int roleid, String email) {
        try {
            return userRepository.updateRole(roleid, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStatus(int status, String email) {
        try {
            return userRepository.updateStatus(status, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkExistAccount(String email) {
        try {
            return userRepository.checkExistAccount(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getNewId() {

        try {
            return userRepository.getNewId();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void insertUser(int id, String image, String fullname, boolean gender, String email, String address, int status, String mobile) {

        try {
            userRepository.insertUser(id, image, fullname, gender, email, address, status, mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(String email) {

        try {
            return userRepository.addUser(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUserAscById() {
        List<User> list;
        try {
            list = this.userRepository.getUserAscById();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> getUserAscByName() {
        List<User> list;
        try {
            list = this.userRepository.getUserAscByName();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> getUserDescByName() {
        List<User> list;
        try {
            list = this.userRepository.getUserDescByName();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<User> getUserDescById() {
        List<User> list;
        try {
            list = this.userRepository.getUserDescById();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int countTotalUser() {

        try {
            return userRepository.countTotalUser();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;

    }
    
    public List<User> getAuthor() {
        List<User> list;
        try {
            list = this.userRepository.getAuthor();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
