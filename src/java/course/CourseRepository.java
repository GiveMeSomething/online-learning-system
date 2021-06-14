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
import common.entities.PricePackage;
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
