/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.Category;
import common.entities.CourseRegistation;
import common.entities.Gender;
import common.entities.Status;
import common.entities.User;
import common.utilities.Repository;
import java.sql.Date;
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

    public CourseRegistation getRegistrationDetail(int userId, int courseId) throws SQLException {
        this.connectDatabase();
        List<CourseRegistation> list = new ArrayList<>();
        String SQL = "SELECT c.title, pp.name, pp.list_price, u.full_name, u.gender, u.email,\n"
                + "	u.mobile, date(uc.registration_time) as 'valid_from',\n"
                + "    DATE_ADD(date(uc.registration_time), INTERVAL (pp.duration * 31) DAY) AS valid_to,\n"
                + "    uc.registration_status\n"
                + "    FROM user_course uc\n"
                + "    JOIN db_ite1.price_package pp ON uc.valid_to = pp.id\n"
                + "    JOIN db_ite1.course c ON uc.course_id = c.id\n"
                + "    JOIN db_ite1.user u ON u.id = uc.user_id\n"
                + "    WHERE user_id = ? AND course_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User(result.getString("full_name"),
                        result.getInt("gender") == 1 ? Gender.MALE : Gender.FEMALE,
                        result.getString("email"), Status.ACTIVE, result.getString("mobile"));
                return new CourseRegistation(courseId, result.getString("title"), user,
                        result.getString("name"), result.getDouble("list_price"),
                        result.getInt("registration_status"), 
                        result.getDate("valid_from"), result.getDate("valid_to"));
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
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

}
