/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author Asus
 */
public class DAO  {
     Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getUserAscById() throws Exception {
        String query = "SELECT \n"
                + "    *\n"
                + "FROM\n"
                + "    db_ite1.user\n"
                + "ORDER BY ID;";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public List<User> getUserAscByName() throws Exception {
        String query = "SELECT \n"
                + "    *\n"
                + "FROM\n"
                + "    db_ite1.user\n"
                + "ORDER BY fullname ASC;";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public List<User> getUserDescByName() throws Exception {
        String query = "SELECT \n"
                + "    *\n"
                + "FROM\n"
                + "    db_ite1.user\n"
                + "ORDER BY fullname DESC;";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public List<User> getUserDescById() throws Exception {
        String query = "SELECT \n"
                + "    *\n"
                + "FROM\n"
                + "    db_ite1.user\n"
                + "ORDER BY ID DESC;";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public int countTotalUser() throws SQLException {
        String query = "SELECT \n"
                + "    COUNT(*)\n"
                + "FROM\n"
                + "    db_ite1.user";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return 0;
    }

    public List<User> pagingUser(int index) throws SQLException {
        String query = "SELECT * FROM db_ite1.user LIMIT 5 OFFSET ?;";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public List<User> searchUser(String txt) throws SQLException {
        String query = "SELECT \n"
                + "    *\n"
                + "FROM\n"
                + "    db_ite1.user\n"
                + "WHERE\n"
                + "    fullname LIKE ? OR email LIKE ?\n"
                + "        OR mobile LIKE ?";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            ps.setString(2, "%" + txt + "%");
            ps.setString(3, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public List<User> searchUserByField(String sql) throws SQLException {
        List<User> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM db_ite1.user WHERE id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("image"),
                        rs.getString("fullname"),
                        rs.getBoolean("gender"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("address"),
                        rs.getBoolean("statusid"),
                        rs.getString("mobile"));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return null;
    }

    public Role getRoleById(int id) throws SQLException {
        String query = "SELECT * FROM db_ite1.role WHERE id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Role(rs.getInt("id"),
                        rs.getString("roleName"));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return null;
    }

    public List<Role> getAllRole() throws SQLException {
        List<Role> list = new ArrayList<>();
        String query = "SELECT * FROM db_ite1.role;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Role(rs.getInt("id"),
                        rs.getString("roleName")));
            }
        } catch (Exception e) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return list;
    }

    public void updateUserInformation(int id, String fullname, boolean gender, String address, String mobile) throws SQLException {
        String query = "UPDATE db_ite1.user SET user.fullname = ?, user.gender = ?, user.address = ?, user.mobile = ? WHERE user.id = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setBoolean(2, gender);
            ps.setString(3, address);
            ps.setString(4, mobile);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(conn, ps, rs);
        }
    }

    public void insertAccount(String account, String password) throws SQLException {
        String query = "INSERT INTO db_ite1.account VALUES (?, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser(String image, String fullname, boolean gender, String email, int roleId, String address, int status, String mobile) throws SQLException {
        String query = "INSERT INTO db_ite1.user ("
                + "user.image, "
                + "user.fullname, "
                + "user.gender,"
                + "user.email, "
                + "user.roleid, "
                + "user.address, "
                + "user.statusid, "
                + "user.mobile)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, image);
            ps.setString(2, fullname);
            ps.setBoolean(3, gender);
            ps.setString(4, email);
            ps.setInt(5, roleId);
            ps.setString(6, address);
            ps.setInt(7, status);
            ps.setString(8, mobile);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(conn, ps, rs);
        }
    }

    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            /* Ignored */ }
        try {
            ps.close();
        } catch (Exception e) {
            /* Ignored */ }
        try {
            conn.close();
        } catch (Exception e) {
            /* Ignored */ }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        try {
            dao.updateUserInformation(1, "Duc Long OK", true, "yen lap", "0819853853");
        } catch (Exception e) {
        }
    }
    
    
}
