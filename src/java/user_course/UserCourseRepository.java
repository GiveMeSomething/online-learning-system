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

    /*
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
     */
    public List<CourseRegistation> getCourseUserRegister(int userId, int index) throws SQLException {
        this.connectDatabase();
        List<CourseRegistation> list = new ArrayList<>();
        String SQL = "SELECT \n"
                + "    course_id as id,\n"
                + "    title,\n"
                + "    registration_time,\n"
                + "    user_course.registration_status,\n"
                + "    user_course.registration_time AS valid_from,\n"
                + "    ROUND((price_package.list_price - (price_package.list_price * price_package.discount / 100)),\n"
                + "            2) AS total_cost,\n"
                + "    DATE_ADD(user_course.registration_time,\n"
                + "        INTERVAL (price_package.duration * 31) DAY) AS valid_to,\n"
                + "    price_package.name as package\n"
                + "FROM\n"
                + "    db_ite1.user_course\n"
                + "        JOIN\n"
                + "    course ON user_course.course_id = course.id\n"
                + "        JOIN\n"
                + "    price_package ON user_course.valid_to = price_package.id\n"
                + "WHERE\n"
                + "    user_id = ? LIMIT 5 OFFSET ?;";
//        String SQL = "SELECT  "
//                + "    course.id, "
//                + "    title, "
//                + "    registration_time, "
//                + "    price_package.name AS package, "
//                + "    ROUND((price_package.list_price - (price_package.list_price * price_package.discount / 100)), "
//                + "            2) AS total_cost, "
//                + "    user_course.registration_status, "
//                + "    user_course.registration_time AS valid_from, "
//                + "    DATE_ADD(user_course.registration_time, "
//                + "        INTERVAL (price_package.duration * 31) DAY) AS valid_to "
//                + "FROM "
//                + "    db_ite1.course "
//                + "        JOIN "
//                + "    user_course ON course.id = user_course.course_id "
//                + "        JOIN "
//                + "    course_package ON course.id = course_package.course_id "
//                + "        JOIN "
//                + "    price_package ON price_package.id = course_package.package_id "
//                + "WHERE "
//                + "    user_course.user_id = ? "
//                + "LIMIT 5 OFFSET ?";
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
                + "    uc.registration_status, note\n"
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
                        result.getDate("valid_from"), result.getDate("valid_to"), result.getString("note"));
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
                + "AND DATE_FORMAT(uc.registration_time, \"%y-%m-%d\") <= DATE(NOW()) - INTERVAL ? DAY ";

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
                + "AND DATE_FORMAT(uc.registration_time, \"%y-%m-%d\") <= DATE(NOW()) - INTERVAL ? DAY ";

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

    // Registration detail
    public boolean addRegistrationForFriend(int userId, int courseId, int price, String note) throws SQLException {
        this.connectDatabase();

        String addRegistration = "INSERT INTO user_course(user_id, course_id, valid_to, registration_status, note) "
                + "VALUES(?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addRegistration)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            statement.setInt(3, price);
            statement.setInt(4, 1);
            statement.setString(5, note);
            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean updateUserIdAndStatus(int currenrUserId, int newUserId, int courseId, int status) throws SQLException {
        this.connectDatabase();
        String SQL = "UPDATE user_course "
                + "SET user_id = ?, user_course.registration_status = ? "
                + "WHERE user_id = ? and course_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(SQL)) {
            statement.setInt(1, newUserId);
            statement.setInt(2, status);
            statement.setInt(3, currenrUserId);
            statement.setInt(4, courseId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        UserCourseRepository userCourseRepository = new UserCourseRepository();
        CourseRegistation courseRegistation = userCourseRepository.getRegistrationDetail(17, 6);
        System.out.println(courseRegistation);
    }
}
