/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

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
public class PagingRepository extends Repository {

    public List<Course> pagingCourseList(int cateID,String searchName,int page,String price,String alpha) throws SQLException {
        this.connectDatabase();
        String pagingCourseList = "";
        if(price.equals("1")){
           pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id ORDER BY price asc LIMIT 8 OFFSET ?";  
        }else if(price.equals("0")){
           pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id ORDER BY price desc LIMIT 8 OFFSET ? ";  
        }else if(alpha.equals("ascAlpha")){
           pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id ORDER BY c.title ASC LIMIT 8 OFFSET ? ";  
        }else if(alpha.equals("descAlpha")){
           pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id ORDER BY c.title DESC LIMIT 8 OFFSET ? ";  
        }else{
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ? AND title like ?\n"
                + "GROUP BY c.id LIMIT 8 OFFSET ?";  
        }
       
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(pagingCourseList)) {
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
        } finally {
            this.disconnectDatabase();
        }
    }

    public int countingCourseList(int cateID,String title) throws SQLException {
        this.connectDatabase();
        String countingCourseList = "SELECT COUNT(*) AS count FROM db_ite1.course where category_id = ?"
                + " AND title like ?";
        try (PreparedStatement statement = this.connection.prepareStatement(countingCourseList)) {
            statement.setInt(1, cateID);
            statement.setString(2, "%"+title+"%");
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
        PagingRepository repo = new PagingRepository();
        try {
            List<Course> list = repo.pagingCourseList(1, "", 2, "","descAlpha");
            for (Course o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }

    public List<Course> courseFeature(int cateID) throws SQLException {
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
        } finally {
            this.disconnectDatabase();
        }
    }
}
