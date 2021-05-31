/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utilities.Repository;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class PagingRepository extends Repository {

    public List<Course> pagingCourseList(int cateID,String searchName,int page) throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id LIMIT 8 OFFSET ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, cateID);
            statement.setString(2,"%"+searchName+"%");
            statement.setInt(3, (page - 1) * 8);
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
        } catch (Exception e) {
        }
        return null;
    }

    public int countingCourseList(int cateID,String title) throws Exception {
        this.connectDatabase();
        String sql = "SELECT COUNT(*) AS count FROM db_ite1.course where category_id = ?"
                + " AND title like ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, cateID);
            statement.setString(2, "%"+title+"%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        PagingRepository repo = new PagingRepository();
        try {
            List<Course> list = repo.pagingCourseList(6,"English",1);
            for (Course course : list) {
                System.out.println(course);
            }
        } catch (Exception e) {
        }
    }

    public List<Course> courseFeature(int cateID) throws Exception {
        this.connectDatabase();
        String sql = "SELECT * FROM db_ite1.course where feature = 1 AND category_id = ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
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
        } catch (Exception e) {
        }
        return null;
    }
}
