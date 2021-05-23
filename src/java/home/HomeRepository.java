/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import database.DBContext;
import entities.Course;
import entities.Course1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.Repository;

/**
 *
 * @author Admin
 */
public class HomeRepository extends Repository {

    public List<Course1> getAllCourse() throws Exception {
        this.connectDatabase();
        String sql = "SELECT * FROM db_ite1.course";
        List<Course1> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(
                        new Course1(
                                result.getInt("id"),
                                result.getString("thumbnail"),
                                result.getString("title"),
                                result.getFloat("price"),
                                result.getString("description")
                        ));
               
            }
             return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        HomeRepository repo = new HomeRepository();
        try {
            List<Course1> list = repo.getAllCourse();
            for (Course1 course : list) {
                System.out.println(course);
            }
        } catch (Exception e) {
        }

    }
}
