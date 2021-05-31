/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.common;

import database.DBContext;
import entities.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.Repository;

/**
 *
 * @author AS
 */
public class ChangePassRepository extends Repository {

    public void changePassword(String user_email, String password) throws Exception {
        String sql = "update account set password = ? where user_email = ?";
        this.connectDatabase();
        try (PreparedStatement ps = new DBContext().getConnection().prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, user_email);
            ps.executeUpdate();
        }
    }

    public Boolean checkCurPass(String user_email, String curPassword) throws Exception {
        String password;
        String sql = "select password from account where user_email = ?";
        this.connectDatabase();
        try (PreparedStatement ps = new DBContext().getConnection().prepareStatement(sql)) {
            ps.setString(1, user_email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
                if (password.equals(curPassword)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        ChangePassRepository change = new ChangePassRepository();
        change.changePassword("1233@fpt.vn", "12345");
    }
}
