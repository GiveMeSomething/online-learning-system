/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.Category;
import common.entities.CourseRegistation;
import common.utilities.Repository;
import course.CourseRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserCourseRepository extends Repository {

    public boolean updateStatus(int userId, int courseId, int status) throws SQLException {
        this.connectDatabase();
        String SQL = "UPDATE `db_ite1`.`user_course` SET `registration_status` = ? WHERE `user_id` = ? AND `course_id` = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, status);
            statement.setInt(2, userId);
            statement.setInt(3, courseId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public List<Category> getAllCategory() throws SQLException {
        this.connectDatabase();
        List<Category> list = new ArrayList<>();
        String SQL = "SELECT id, category_name FROM db_ite1.category";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Category(result.getInt("id"), result.getString("category_name")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countCourseUserRegis() throws SQLException {
        this.connectDatabase();
        String SQL = "SELECT  "
                + "    COUNT(*) "
                + "FROM "
                + "    db_ite1.course "
                + "        JOIN "
                + "    user_course ON course.id = user_course.course_id "
                + "        JOIN "
                + "    course_package ON course.id = course_package.course_id "
                + "        JOIN "
                + "    price_package ON price_package.id = course_package.package_id "
                + "WHERE "
                + "    user_course.user_id = 1";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<CourseRegistation> getCourseUserRegister(int userId, int index) throws SQLException {
        this.connectDatabase();
        List<CourseRegistation> list = new ArrayList<>();
        String SQL = "SELECT  "
                + "    course.id, "
                + "    title, "
                + "    registration_time, "
                + "    price_package.name AS package, "
                + "    ROUND((price_package.list_price - (price_package.list_price * price_package.discount / 100)), "
                + "            2) AS total_cost, "
                + "    user_course.registration_status, "
                + "    user_course.registration_time AS valid_from, "
                + "    DATE_ADD(user_course.registration_time, "
                + "        INTERVAL (price_package.duration * 31) DAY) AS valid_to "
                + "FROM "
                + "    db_ite1.course "
                + "        JOIN "
                + "    user_course ON course.id = user_course.course_id "
                + "        JOIN "
                + "    course_package ON course.id = course_package.course_id "
                + "        JOIN "
                + "    price_package ON price_package.id = course_package.package_id "
                + "WHERE "
                + "    user_course.user_id = ? "
                + "LIMIT 5 OFFSET ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, userId);
            statement.setInt(2, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
//                id, title, registration_time, package, total_cost, registration_status, valid_from, valid_to
                list.add(new CourseRegistation(result.getInt("id"), result.getString("title"),
                        result.getDate("registration_time"), result.getString("package"), result.getDouble("total_cost"), result.getInt("registration_status"),
                        result.getDate("valid_from"), result.getDate("valid_to")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public List<CourseRegistation> searchCourseByCategory(int userId, int categoryId) throws SQLException {
        this.connectDatabase();
        List<CourseRegistation> list = new ArrayList<>();
        String SQL = "SELECT  "
                + "    course.id, "
                + "    title, "
                + "    registration_time, "
                + "    price_package.name AS package, "
                + "    ROUND((price_package.list_price - (price_package.list_price * price_package.discount / 100)), "
                + "            2) AS total_cost, "
                + "    user_course.registration_status, "
                + "    user_course.registration_time AS valid_from, "
                + "    DATE_ADD(user_course.registration_time, "
                + "        INTERVAL (price_package.duration * 31) DAY) AS valid_to "
                + "FROM "
                + "    db_ite1.course "
                + "        JOIN "
                + "    user_course ON course.id = user_course.course_id "
                + "        JOIN "
                + "    course_package ON course.id = course_package.course_id "
                + "        JOIN "
                + "    price_package ON price_package.id = course_package.package_id "
                + "WHERE "
                + "    user_course.user_id = ? ";
        if (categoryId != 0) {
            SQL += " AND category_id = ? ";
        }
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, userId);
            if (categoryId != 0) {
                statement.setInt(2, categoryId);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
//                id, title, registration_time, package, total_cost, registration_status, valid_from, valid_to
                list.add(new CourseRegistation(result.getInt("id"), result.getString("title"),
                        result.getDate("registration_time"), result.getString("package"), result.getDouble("total_cost"), result.getInt("registration_status"),
                        result.getDate("valid_from"), result.getDate("valid_to")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public List<CourseRegistation> searchCourseByTitle(int userId, String txtSearch) throws SQLException {
        this.connectDatabase();
        List<CourseRegistation> list = new ArrayList<>();
        String SQL = "SELECT  "
                + "    course.id, "
                + "    title, "
                + "    registration_time, "
                + "    price_package.name AS package, "
                + "    ROUND((price_package.list_price - (price_package.list_price * price_package.discount / 100)), "
                + "            2) AS total_cost, "
                + "    user_course.registration_status, "
                + "    user_course.registration_time AS valid_from, "
                + "    DATE_ADD(user_course.registration_time, "
                + "        INTERVAL (price_package.duration * 31) DAY) AS valid_to "
                + "FROM "
                + "    db_ite1.course "
                + "        JOIN "
                + "    user_course ON course.id = user_course.course_id "
                + "        JOIN "
                + "    course_package ON course.id = course_package.course_id "
                + "        JOIN "
                + "    price_package ON price_package.id = course_package.package_id "
                + "WHERE "
                + "    user_course.user_id = ? "
                + "        AND title like ?";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, userId);
            statement.setString(2, "%" + txtSearch + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
//                id, title, registration_time, package, total_cost, registration_status, valid_from, valid_to
                list.add(new CourseRegistation(result.getInt("id"), result.getString("title"),
                        result.getDate("registration_time"), result.getString("package"), result.getDouble("total_cost"), result.getInt("registration_status"),
                        result.getDate("valid_from"), result.getDate("valid_to")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    //Dashboard
    public int countRegistationStatus(int status, int date) throws SQLException {
        this.connectDatabase();
        String SQL = "SELECT COUNT(*) AS count from db_ite1.user_course uc where registration_status = ? "
                + "AND DATE_FORMAT(uc.registration_time, \"%y-%m-%d\") "
                + "BETWEEN  DATE_SUB(CURDATE(), interval ? day) "
                + "AND CURDATE()";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, status);
            statement.setInt(2, date);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int countTotalRegistationSuccess(int date) throws SQLException {
        this.connectDatabase();
        String SQL = " SELECT COUNT(*) as count from db_ite1.user_course uc where registration_status = 2 "
                + "AND uc.registration_time "
                + "NOT BETWEEN  DATE_SUB(CURDATE(), interval ? day) "
                + "AND CURDATE()";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, date);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int countingTotalRegistration(int date) throws SQLException {
        this.connectDatabase();
        String countingTotalRegistration = "SELECT COUNT(*) AS count FROM db_ite1.user_course uc where uc.registration_time "
                + "NOT BETWEEN  DATE_SUB(CURDATE(), interval ? day) "
                + "AND CURDATE()";
        try (PreparedStatement statement = this.connection.prepareStatement(countingTotalRegistration)) {
            statement.setInt(1, date);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int countingTotalProfit() throws SQLException {
        this.connectDatabase();
        String countingTotalProfit = "SELECT ROUND(SUM(pp.list_price - pp.list_price*pp.discount/100)) as 'Total Profit' from db_ite1.price_package pp "
                + "INNER JOIN db_ite1.user_course uc "
                + "on uc.valid_to = pp.id";
        try (PreparedStatement statement = this.connection.prepareStatement(countingTotalProfit)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("Total Profit");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int countingTotalProfitByCategoryId(int categoryId) throws SQLException {
        this.connectDatabase();
        String countingTotalProfit = "SELECT ROUND(SUM(pp.list_price - pp.list_price*pp.discount/100)) as 'Total Profit' from db_ite1.price_package pp "
                + "INNER JOIN db_ite1.user_course uc "
                + "on uc.valid_to = pp.id "
                + "INNER JOIN db_ite1.course c "
                + "on uc.course_id = c.id "
                + "where c.category_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(countingTotalProfit)) {
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("Total Profit");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

}
