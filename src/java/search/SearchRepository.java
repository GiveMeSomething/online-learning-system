/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import common.entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.utilities.Repository;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class SearchRepository extends Repository {

    public List<Course> searchCourse(int cateID,String searchName) throws SQLException {
        this.connectDatabase();
        String searchCourse = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)\n"
                + "AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title LIKE ?\n"
                + "GROUP BY c.id LIMIT 8 OFFSET 0";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setInt(1, cateID);
            statement.setString(2, "%" + searchName + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getFloat("price"),
                        result.getString("tag")
                ));

            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<Course> getCourseFeature(int cateID) throws SQLException {
        this.connectDatabase();
        String getCourseFeature = "SELECT * FROM db_ite1.course where feature = 1 AND category_id = ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getCourseFeature)) {
            statement.setInt(1, cateID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description"),
                        0,
                        result.getString("tag")
                ));

            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }
    
    public int countingCourseListSearch(int cateID,String searchName) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT COUNT(*) AS count FROM db_ite1.course "
                + "where category_id = ? AND title LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, cateID);
            statement.setString(2, "%"+searchName+"%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

   
    
    public static void main(String[] args) throws Exception {
        SearchRepository repo = new SearchRepository();
        try {
            int num = repo.countingCourseListSearch(3, "Digital");
            System.out.println(num);
        } catch (Exception e) {
        }
    }
}
