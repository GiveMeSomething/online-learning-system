/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import common.entities.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.utilities.Repository;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class HomeRepository extends Repository{
     //GET ALL CATEGORY
    public List<Category> getAllCategory() throws Exception {
        this.connectDatabase();
        String sql = "SELECT * FROM db_ite1.category;";
        List<Category> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Category(result.getInt("id"),result.getString("category_name")));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) throws Exception{
        HomeRepository repo = new HomeRepository();
        List<Category> list = repo.getAllCategory();
        for (Category o : list) {
            System.out.println(o);
        }
    }
}
