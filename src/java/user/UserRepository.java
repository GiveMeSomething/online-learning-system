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
import java.util.ArrayList;
import java.util.List;

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

    public User getUser(int id) throws SQLException {
        this.connectDatabase();

        String getUser = "SELECT id, image, full_name, gender, email, address, status_id, mobile "
                + "FROM user WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(getUser)) {
            statement.setInt(1, id);

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

    public boolean updateUser(User userUpdate) throws SQLException {
        this.connectDatabase();
        String sql = "UPDATE db_ite1.user SET "
                + "image = ?, "
                + "full_name=?, "
                + "gender=?, "
                + "address=?, "
                + "status_id=?, "
                + "mobile=? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, userUpdate.getImage());
            statement.setString(2, userUpdate.getName());
            statement.setInt(3, Gender.valueOf(userUpdate.getGender()));
            statement.setString(4, userUpdate.getAddress());
            statement.setInt(5, Status.valueOf(userUpdate.getStatus()));
            statement.setString(6, userUpdate.getMobile());
            statement.setInt(7, userUpdate.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<User> getUserAscById() throws SQLException {
        String getUserAscById = "SELECT "
                + "id, image, full_name, gender, email, role_id, address, status_id, mobile "
                + "FROM "
                + "db_ite1.user "
                + "ORDER BY ID; ";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getUserAscById)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userList.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile"))
                );
            }
        } finally {
            this.disconnectDatabase();
        }
        return userList;
    }

    public List<User> getUserAscByName() throws SQLException {
        String getUserAscByName = "SELECT "
                + "id, image, full_name, gender, email, role_id, address, status_id, mobile "
                + "FROM "
                + "db_ite1.user "
                + "ORDER BY full_name ASC;";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getUserAscByName)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userList.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } finally {
            this.disconnectDatabase();
        }
        return userList;
    }

    public List<User> getUserDescByName() throws SQLException {
        String getUserDescByName = "SELECT "
                + "id, image, full_name, gender, email, role_id, address, status_id, mobile "
                + "FROM "
                + "db_ite1.user "
                + "ORDER BY fullname DESC; ";
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getUserDescByName)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public List<User> getUserDescById() throws SQLException {
        String getUserDescById = "SELECT "
                + "id, image, full_name, gender, email, role_id, address, status_id, mobile "
                + "FROM "
                + "db_ite1.user "
                + "ORDER BY ID DESC; ";
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getUserDescById)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countTotalUser() throws SQLException {
        String countTotalUser = "SELECT "
                + "COUNT(*) "
                + "FROM "
                + "db_ite1.user";
        try (PreparedStatement statement = this.connection.prepareStatement(countTotalUser)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<User> pagingUser(int index) throws SQLException {
        String pagingUser = "SELECT id, image, full_name, gender, email, role_id, address, status_id, mobile FROM db_ite1.user LIMIT 5 OFFSET ?;";
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(pagingUser)) {

            statement.setInt(1, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } catch (Exception e) {
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public List<User> searchUser(String txt) throws SQLException {
        String searchUser = "SELECT "
                + "id, image, full_name, gender, email, role_id, address, status_id, mobile "
                + "FROM "
                + "db_ite1.user join account on user.email = account.user_email "
                + "WHERE "
                + "full_name LIKE ? OR email LIKE ? "
                + "OR mobile LIKE ? ";
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(txt)) {

            statement.setString(1, "%" + txt + "%");
            statement.setString(2, "%" + txt + "%");
            statement.setString(3, "%" + txt + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } catch (Exception e) {
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public List<User> searchUserByField(String searchUserByField, int index) throws SQLException {
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchUserByField)) {

            statement.setInt(1, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new User(
                                result.getInt("id"),
                                result.getString("image"),
                                result.getString("full_name"),
                                result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                                result.getString("email"),
                                result.getString("address"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                result.getString("mobile")
                        )
                );
            }
        } catch (Exception e) {
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countSearchUserByField(String countSearchUserByField) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(countSearchUserByField)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public User getUserById(int id) throws SQLException {
        String getUserById = "SELECT id, image, full_name, gender, email, role_id, address, status_id, mobile FROM db_ite1.user join account on user.email = account.user_email WHERE id = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(getUserById)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
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
        } catch (Exception e) {
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public void updateUserInformation(int id, String fullname, boolean gender, String address, String mobile) throws SQLException {
        String updateUserInformation = "UPDATE db_ite1.user SET user.full_name = ?, user.gender = ?, user.address = ?, user.mobile = ? WHERE user.id = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(mobile)) {

            statement.setString(1, fullname);
            statement.setBoolean(2, gender);
            statement.setString(3, address);
            statement.setString(4, mobile);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.disconnectDatabase();
        }
    }

    public void addUser(String email) {
        String addUser = "INSERT INTO db_ite1.user join account on user.email = account.user_email"
                + " (email, role_id, status_id) VALUES (?,2, 0);";
        try (PreparedStatement statement = this.connection.prepareStatement(addUser)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*  TODO: insert account

    public boolean insertAccount(String account, String password, int roleId) throws SQLException {
        String insertAccount = "INSERT INTO db_ite1.account VALUES (?,?, ?);";
        try (PreparedStatement statement = this.connection.prepareStatement(insertAccount)) {

            statement.setString(1, account);
            statement.setString(2, password);
            statement.setInt(3, roleId);
            if (statement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.disconnectDatabase();
        }
        ;
        return false;
    }
     */
    public boolean updateRole(int uid, int roleid, boolean status) throws SQLException {
        String updateRole = "UPDATE db_ite1.user join account on user.email = account.user_email SET roleid = ?, statusid = ? WHERE id = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(updateRole)) {

            statement.setInt(1, roleid);
            statement.setBoolean(2, status);
            statement.setInt(3, uid);
            if (statement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public boolean checkExistAccount(String email) throws SQLException {
        String checkExistAccount = "SELECT * FROM db_ite1.account where user_email = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(checkExistAccount)) {

            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public int getNewId() throws SQLException {
        String getNewId = "select max(user.id) from db_ite1.user";
        try (PreparedStatement statement = this.connection.prepareStatement(getNewId)) {

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void insertUser(int id, String image, String fullname, boolean gender, String email, String address, int status, String mobile) throws SQLException {
        String insertUser = "INSERT INTO db_ite1.user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(insertUser)) {
            statement.setInt(1, id);
            statement.setString(2, image);
            statement.setString(3, fullname);
            statement.setBoolean(4, gender);
            statement.setString(5, email);
            statement.setString(6, address);
            statement.setInt(7, status);
            statement.setString(8, mobile);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.disconnectDatabase();
        }
    }

}
