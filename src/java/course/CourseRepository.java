/**
 *
 * @author Dinh Kong Thanh
 */
package course;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import common.entities.Category;
import common.entities.Course;
import common.entities.PricePack;
import common.entities.PricePackage;
import common.entities.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.utilities.Repository;
import java.sql.SQLException;

public class CourseRepository extends Repository {

    public Course getCourse(int id) throws SQLException {
        this.connectDatabase();
        String getCourse = "SELECT c.id, c.thumbnail, c.title, c.description, c.tag, ca.category_name, MIN(pp.list_price) "
                + "AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "ON c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "ON p.package_id = pp.id "
                + "INNER JOIN db_ite1.category ca "
                + "ON ca.id = c.category_id "
                + "WHERE c.id = ? "
                + "GROUP BY c.id ";

        try (PreparedStatement statement = this.connection.prepareStatement(getCourse)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getFloat("price"),
                        result.getString("category_name"),
                        result.getString("description"),
                        result.getString("tag"));

            }

            return null;
        } finally {
            this.disconnectDatabase();
        }
    }
       public List<PricePack> pagingPricePackage(int index) throws SQLException {
        this.connectDatabase();
        List<PricePack> list = new ArrayList<>();
        String getAllPricePackage = "SELECT id, duration, name, list_price, status_id, description, discount FROM db_ite1.price_package LIMIT 5 OFFSET ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getAllPricePackage)) {
            statement.setInt(1, (index - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new PricePack(result.getInt("id"),
                        result.getInt("duration"),
                        result.getString("name"),
                        result.getDouble("list_price"),
                        result.getInt("status_id") == 1 ? Status.ACTIVE : Status.INACTIVE,
                        result.getString("description"),
                        result.getDouble("discount")));
            }
        } finally {
            this.disconnectDatabase();
        }
        return list;
    }

    public int countTotalPricePackage() throws SQLException {
        this.connectDatabase();
        String query = "SELECT COUNT(*) FROM db_ite1.price_package";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public boolean addPackage(int duration, String name, double price, int status, String descriptions, double discount) throws SQLException {
        this.connectDatabase();
        String query = "INSERT INTO `db_ite1`.`price_package` "
                + "(`duration`, `name`, `list_price`, `status_id`, `description`, `discount`) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, duration);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, status);
            statement.setString(5, descriptions);
            statement.setDouble(6, discount);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public boolean deletePackage(int id) throws SQLException {
        this.connectDatabase();
        String query = "DELETE FROM `db_ite1`.`price_package` WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public boolean editPackage(int id, int duration, String name, double price, int status, String descriptions, double discount) throws SQLException {
        this.connectDatabase();
        String query = "UPDATE `db_ite1`.`price_package` SET `duration` = ?, "
                + "`name` = ?, "
                + "`list_price` = ?, "
                + "`status_id` = ?, "
                + "`description` = ?, `discount` = ? WHERE `id` = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, duration);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, status);
            statement.setString(5, descriptions);
            statement.setDouble(6, discount);
            statement.setInt(7, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public ArrayList<PricePackage> getCoursePackage(int id) throws SQLException {
        this.connectDatabase();

        String getCoursePackage = "SELECT pp.id, pp.name, pp.list_price "
                + "FROM course c INNER JOIN course_package cp on c.id = cp.course_id "
                + "INNER JOIN price_package pp on cp.package_id = pp.id "
                + "WHERE c.id = ? "
                + "GROUP BY pp.id";
        try (PreparedStatement statement = this.connection.prepareStatement(getCoursePackage)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            ArrayList<PricePackage> pricePackages = new ArrayList<>();
            while (result.next()) {
                pricePackages.add(
                        new PricePackage(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getFloat("list_price")
                        )
                );
            }

            return pricePackages;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<Course> getCourses(String category) throws SQLException {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1.category ca "
                + "on ca.id = c.category_id "
                + "where ca.category_name = ? "
                + "GROUP BY c.id "
                + "ORDER BY RAND() "
                + "LIMIT 3";

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, category);
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

    public List<Course> getFeaturedCourse() throws SQLException {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,pp.list_price "
                + "AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1.category ca "
                + "on ca.id = c.category_id "
                + "where c.feature = '1' and c.id > '50' "
                + "GROUP BY c.id "
                + "ORDER BY RAND() "
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
        }
    }

    public List<Course> getSiderCourseDetail() throws SQLException {
        this.connectDatabase();
        String sql = "SELECT c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + " AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1.category ca "
                + "on ca.id = c.category_id "
                + "where c.feature = '1' "
                + "GROUP BY c.id "
                + "ORDER BY RAND() "
                + "limit 3 ";

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

    public List<Course> pagingCourseList(int cateID, String searchName, int page, String price, String alpha) throws SQLException {
        this.connectDatabase();
        String pagingCourseList = "";
        if (price.equals("1") && alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price asc,title asc LIMIT 8 OFFSET ?";
        } else if (price.equals("1") && alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price asc,title desc LIMIT 8 OFFSET ?";
        } else if (price.equals("0") && alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price desc,title asc LIMIT 8 OFFSET ?";
        } else if (price.equals("0") && alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price desc,title desc LIMIT 8 OFFSET ?";
        } else if (price.equals("1")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price asc LIMIT 8 OFFSET ?";
        } else if (price.equals("0")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY price desc LIMIT 8 OFFSET ? ";
        } else if (alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY c.title ASC LIMIT 8 OFFSET ? ";
        } else if (alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c\n"
                    + "INNER JOIN db_ite1.course_package p\n"
                    + "on c.id = p.course_id\n"
                    + "INNER JOIN db_ite1.price_package pp\n"
                    + "on p.package_id = pp.id\n"
                    + "WHERE c.category_id = ? AND title like ?\n"
                    + "GROUP BY c.id ORDER BY c.title DESC LIMIT 8 OFFSET ? ";
        } else {
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
            statement.setString(2, "%" + searchName + "%");
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

    public int countingCourseListSearch(int cateID, String searchName) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT COUNT(*) AS count FROM db_ite1.course "
                + "where category_id = ? AND title LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, cateID);
            statement.setString(2, "%" + searchName + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<Course> searchCourse(int cateID, String searchName) throws SQLException {
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

    public List<Category> getAllCategory() throws SQLException {
        this.connectDatabase();
        String getAllCategory = "SELECT * FROM db_ite1.category;";
        List<Category> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAllCategory)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Category(result.getInt("id"), result.getString("category_name")));
            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }

//    Subject part
    public Course getSubject(int id) throws SQLException {
        this.connectDatabase();
        String getCourse = "SELECT c.id, c.thumbnail, c.title, c.description, ca.category_name,c.feature, c.status_id, c.owner "
                + "from db_ite1.course c "
                + "INNER JOIN db_ite1.status s "
                + "on c.status_id = s.id "
                + "INNER JOIN db_ite1.course_package p "
                + "ON c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp  "
                + "ON p.package_id = pp.id "
                + "INNER JOIN db_ite1.category ca "
                + "ON ca.id = c.category_id "
                + "WHERE c.id = ? "
                + "GROUP BY c.id";

        try (PreparedStatement statement = this.connection.prepareStatement(getCourse)) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getInt("owner"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("category_name"),
                        result.getBoolean("feature"));
            }

            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean updateSubjectInformation(String courseName, String description,int owner, int status_id, int category_id,int feature, int id) throws SQLException {
        this.connectDatabase();

        String updateUserInformation = "UPDATE db_ite1.course "
                + "SET course.title = ?, "
                + "course.description = ?, "
                + "course.owner = ?, "
                + "course.status_id = ?, "
                + "course.category_id = ?, "
                + "course.feature = ? "
                + "WHERE course.id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateUserInformation)) {
            statement.setString(1, courseName);
            statement.setString(2, description);
            statement.setInt(3, owner);
            statement.setInt(4, status_id);
            statement.setInt(5, category_id);
            statement.setInt(6, feature);
            statement.setInt(7, id);
            statement.executeUpdate();
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }

    }

    public static void main(String[] args) throws Exception {
        CourseRepository repo = new CourseRepository();
        try {
            List<Category> list = repo.getAllCategory();
            for (Category o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }


}
