/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courselist;

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
public class CourseListRepository extends Repository {

    public List<Course> getCourseByCateID(int cateID) throws SQLException {
        this.connectDatabase();
        String getCourseByCateID = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)"
                + " AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ?\n"
                + "GROUP BY c.id\n";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getCourseByCateID)) {
            statement.setInt(1, cateID);
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

    public List<Course> pagingCourseList(int cateID, int page) throws SQLException {
        this.connectDatabase();
        String pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                + "INNER JOIN db_ite1.course_package p\n"
                + "on c.id = p.course_id\n"
                + "INNER JOIN db_ite1.price_package pp\n"
                + "on p.package_id = pp.id\n"
                + "WHERE c.category_id = ?\n"
                + "GROUP BY c.id LIMIT 8 OFFSET ?;";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(pagingCourseList)) {
            statement.setInt(1, cateID);
            statement.setInt(2, (page - 1) * 8);
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

    public int countingCourseList(int cateID) throws SQLException {
        this.connectDatabase();
        String countingCourseList = "SELECT COUNT(*) AS count FROM db_ite1.course where category_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(countingCourseList)) {
            statement.setInt(1, cateID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
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

    public List<Course> searchCourse(String searchName, int cateID) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course WHERE title LIKE ? AND category_id = ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setString(1, "%" + searchName + "%");
            statement.setInt(2, cateID);
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

    public List<Course> sortCoursePrice(int cateID, String price) throws SQLException {
        this.connectDatabase();
        String sortCoursePrice = "";
        if (price.equals("1")) {
            sortCoursePrice = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)"
                    + " AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ?\n"
                    + "GROUP BY c.id\n ORDER BY MIN(pp.list_price) ASC LIMIT 8";
        } else {
            sortCoursePrice = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)"
                    + " AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ?\n"
                    + "GROUP BY c.id\n ORDER BY MIN(pp.list_price) DESC LIMIT 8";
        }
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sortCoursePrice)) {
            statement.setInt(1, cateID);
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

    public List<Course> sortCourseAlpha(int cateID, String alpha) throws SQLException {
        this.connectDatabase();
        String sortCourseAlpha = "";
        if (alpha.equals("ascAlpha")) {
            sortCourseAlpha = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)\n"
                    + "AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? "
                    + "GROUP BY c.id ORDER BY c.title ASC LIMIT 8";
        } else {
            sortCourseAlpha = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price)\n"
                    + "AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? "
                    + "GROUP BY c.id ORDER BY c.title DESC LIMIT 8";
        }
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sortCourseAlpha)) {
            statement.setInt(1, cateID);
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

    public static void main(String[] args) throws Exception {
        CourseListRepository repo = new CourseListRepository();
        try {
            List<Course> list = repo.sortCourseAlpha(1, "ascAlpha");
            for (Course o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }
}
