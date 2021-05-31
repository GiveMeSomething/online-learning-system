/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userprofile;

import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.Repository;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class UserProfileRepository extends Repository {

    public User getUserProfile(int id) throws Exception {
        this.connectDatabase();
        String sql = "select * from user where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("image"),
                        result.getString("full_name"),
                        result.getInt("gender"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getInt("status_id"),
                        result.getString("mobile")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateUserProfile(String image,String fullName,int gender,String address,
            int status,String mobile,int id) {
        this.connectDatabase();
        String sql = "UPDATE db_ite1.user SET \n"
                + "image = ?,\n"
                + "full_name=?,\n"
                + "gender=?,\n"
                + "address=?,\n"
                + "status_id=?,\n"
                + "mobile=?\n"
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, image);
            statement.setString(2, fullName);
            statement.setInt(3, gender);
            statement.setString(4, address);
            statement.setInt(5, status);
            statement.setString(6, mobile);
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        UserProfileRepository repo = new UserProfileRepository();
        try {
            User user = repo.getUserProfile(1);
            System.out.println(user);
        } catch (Exception e) {
        }
    }
}
