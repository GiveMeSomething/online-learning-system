/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import database.DBContext;
import entities.Course;
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

    public List<Course> getITCourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Software Engineering' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getBusinessCourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Economy' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getMarketingCourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Digital Marketing' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getAICourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Artificial Intelligence' and c.id > '43' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getIACourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Information Assurance' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getLanguageCourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = 'Language' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Course> getStudentAreViewingCourse() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,pp.list_price "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where c.feature = '1' and c.id > '50' "
                + "GROUP BY c.id "
                +"ORDER BY RAND() " 
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    
    
    public List<Course> getSiderCourseDetail() throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                              +" AS price from db_ite1_updated.course c "
				+"INNER JOIN db_ite1_updated.course_package p "
                                +"on c.id = p.course_id "
                                +"INNER JOIN db_ite1_updated.price_package pp "
                               +"on p.package_id = pp.id "
                                +"INNER JOIN db_ite1_updated.category ca "
                               +"on ca.id = c.category_id "
                               +"where c.feature =1 " 
                                +"GROUP BY c.id "
                                +"ORDER BY RAND() " 
                                +"limit 3 ";

       List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                 list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag")
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
            List<Course> list = repo.getSiderCourseDetail();
            for (Course course : list) {
                System.out.println(course);
            }
                ;
            
        } catch (Exception e) {
        }

    }
}
