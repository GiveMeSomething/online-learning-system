/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.Category;
import common.entities.CourseRegistation;
import common.entities.Status;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public ArrayList<ArrayList<String>> getCourseRegistations(String keyword, Date from, Date to, String orderBy, int userId) throws SQLException {
        this.connectDatabase();

        String options = "WHERE 1 = 1 ";

        if (keyword != null && !keyword.equals("")) {
            options += "AND c.title LIKE '%" + keyword + "%' OR u.email LIKE '%" + keyword + "%' ";
        }

        if (from != null && to != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String validFrom = dateFormat.format(from);
            String validTo = dateFormat.format(to);

            options += "AND (valid_from BETWEEN '" + validFrom + "' AND '" + validTo + "') "
                    + "AND (valid_to BETWEEN '" + validFrom + "' AND '" + validTo + "') ";
        }

        if (userId != -1) {
            options += "AND c.owner = " + userId + " ";
        }

        if (orderBy != null && !orderBy.equals("")) {
            options += "ORDER BY " + orderBy + " ";
        }

        String sql = "SELECT uc.course_id, u.email, uc.registration_time, c.title, pp.name, "
                + "TRUNCATE((pp.list_price * pp.discount) / 100, 2) as cost, uc.registration_status, "
                + "uc.registration_time as valid_from, DATE_ADD(uc.registration_time, INTERVAL pp.duration MONTH) as valid_to "
                + "FROM user_course uc LEFT OUTER JOIN user u on uc.user_id = u.id "
                + "LEFT OUTER JOIN course c on uc.course_id = c.id "
                + "LEFT OUTER JOIN price_package pp on uc.valid_to = pp.id "
                + options;
        System.out.println(sql);
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();

            ArrayList<ArrayList<String>> resultList = new ArrayList<>();
            while (result.next()) {
                ArrayList<String> dataRow = new ArrayList<>();

                dataRow.add(result.getString("email"));
                dataRow.add(result.getString("registration_time"));
                dataRow.add(result.getString("title"));
                dataRow.add(result.getString("name"));
                dataRow.add(result.getString("cost"));
                dataRow.add(
                        result.getInt("registration_status") == 0
                        ? Status.INACTIVE.toString()
                        : Status.ACTIVE.toString()
                );
                dataRow.add(result.getString("valid_from"));
                dataRow.add(result.getString("valid_to"));

                resultList.add(dataRow);
            }

            return resultList;
        } finally {
            this.disconnectDatabase();
        }
    }

}
