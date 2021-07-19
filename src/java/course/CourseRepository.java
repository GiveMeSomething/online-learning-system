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
import common.entities.Account;
import common.entities.Category;
import common.entities.Course;
import common.entities.CourseStatus;
import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.PricePack;
import common.entities.PricePackage;
import common.entities.Role;
import common.entities.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.utilities.Repository;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;

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
                + "where c.feature = '1' "
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

    public List<Course> getCourseByCategoryId(int categoryId) throws SQLException {
        this.connectDatabase();
        String getCourseByCateID = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) "
                + "AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "WHERE c.category_id = ? "
                + "GROUP BY c.id ";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getCourseByCateID)) {
            statement.setInt(1, categoryId);
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

    public List<Course> pagingCourseList(int categoryId, int page) throws SQLException {
        this.connectDatabase();
        String pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "WHERE c.category_id = ? "
                + "GROUP BY c.id LIMIT 8 OFFSET ?;";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(pagingCourseList)) {
            statement.setInt(1, categoryId);
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

    public int countingCourseList(int categoryId) throws SQLException {
        this.connectDatabase();
        String countingCourseList = "SELECT COUNT(*) AS count FROM db_ite1.course where category_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(countingCourseList)) {
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<Course> getCourseFeature(int categoryId) throws SQLException {
        this.connectDatabase();
        String getCourseFeature = "SELECT * FROM db_ite1.course where feature = 1 AND category_id = ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getCourseFeature)) {
            statement.setInt(1, categoryId);
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

    public List<Course> searchCourse(String searchName, int categoryId) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course WHERE title LIKE ? AND category_id = ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setString(1, "%" + searchName + "%");
            statement.setInt(2, categoryId);
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

    public Course checkCourseExist(String searchName, int cateID) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course WHERE title LIKE ? AND category_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setString(1, "%" + searchName + "%");
            statement.setInt(2, cateID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description"),
                        0,
                        result.getString("tag")
                );
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<Course> searchCourse(int categoryId, String searchName) throws SQLException {
        this.connectDatabase();
        String searchCourse = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) "
                + "AS price from db_ite1.course c "
                + "INNER JOIN db_ite1.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1.price_package pp "
                + "on p.package_id = pp.id "
                + "WHERE c.category_id = ? AND title LIKE ? "
                + "GROUP BY c.id LIMIT 8 OFFSET 0";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setInt(1, categoryId);
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

    public List<Course> pagingCourseList(int categoryId, String searchName, int page, String price, String alpha) throws SQLException {
        this.connectDatabase();
        String pagingCourseList = "";
        if (price.equals("1") && alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price asc,title asc LIMIT 8 OFFSET ?";
        } else if (price.equals("1") && alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price asc,title desc LIMIT 8 OFFSET ?";
        } else if (price.equals("0") && alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price desc,title asc LIMIT 8 OFFSET ?";
        } else if (price.equals("0") && alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price desc,title desc LIMIT 8 OFFSET ?";
        } else if (price.equals("1")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price asc LIMIT 8 OFFSET ?";
        } else if (price.equals("0")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY price desc LIMIT 8 OFFSET ? ";
        } else if (alpha.equals("ascAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY c.title ASC LIMIT 8 OFFSET ? ";
        } else if (alpha.equals("descAlpha")) {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id ORDER BY c.title DESC LIMIT 8 OFFSET ? ";
        } else {
            pagingCourseList = "select c.id,c.thumbnail,c.title,c.description,c.tag,MIN(pp.list_price) AS price from db_ite1.course c "
                    + "INNER JOIN db_ite1.course_package p "
                    + "on c.id = p.course_id "
                    + "INNER JOIN db_ite1.price_package pp "
                    + "on p.package_id = pp.id "
                    + "WHERE c.category_id = ? AND title like ? "
                    + "GROUP BY c.id LIMIT 8 OFFSET ?";
        }

        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(pagingCourseList)) {
            statement.setInt(1, categoryId);
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

    public int countingCourseListSearch(int categoryId, String searchName) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT COUNT(*) AS count FROM db_ite1.course "
                + "where category_id = ? AND title LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
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

    public boolean deleteSubjectDimensionByCourseId(int courseId, int dimensionId) throws SQLException {
        this.connectDatabase();
        String deleteSubjectDimension = "DELETE FROM db_ite1.course_dimension "
                + "WHERE course_id = ? and dimension_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(deleteSubjectDimension)) {
            statement.setInt(1, courseId);
            statement.setInt(2, dimensionId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public List<Dimension> getSubjectDimensionByCourseId(int courseId) throws SQLException {
        this.connectDatabase();
        String getSubjectDimension = "SELECT cd.dimension_id,dt.dimension_type_name,d.name,d.description "
                + "FROM db_ite1.course_dimension cd "
                + "INNER JOIN dimension d ON cd.dimension_id = d.id "
                + "INNER JOIN dimension_type dt on d.type_id = dt.id "
                + "WHERE course_id = ? ORDER BY type_id";
        List<Dimension> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getSubjectDimension)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Dimension(result.getInt("dimension_id"),
                        result.getString("dimension_type_name"),
                        result.getString("name"),
                        result.getString("description")));
            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addDimension(int typeId, String name, String description) throws SQLException {
        this.connectDatabase();
        String addDimension = "INSERT INTO db_ite1.dimension (type_id,name,description) "
                + "VALUES (?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addDimension)) {
            statement.setInt(1, typeId);
            statement.setString(2, name);
            statement.setString(3, description);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
    }

    public boolean addNewSubject(Course course, InputStream inputStream) throws SQLException {
        this.connectDatabase();

        String addNewSubject = "INSERT INTO course(title, thumbnails, description, "
                + "owner, status_id, category_id, feature) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addNewSubject)) {
            statement.setString(1, course.getCourseName());
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(2, inputStream);
            }
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getOwnerId());
            statement.setInt(5, CourseStatus.valueOf(course.getStatus()));
            statement.setString(6, course.getCategory());
            statement.setBoolean(7, course.isFeature());

            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Dimension getDimensionId(String dimension) throws SQLException {
        this.connectDatabase();
        String getDimensionId = "SELECT * FROM db_ite1.dimension WHERE name = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(getDimensionId)) {
            statement.setString(1, dimension);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Dimension(result.getInt("id"),
                        result.getString("type_id"),
                        result.getString("name"),
                        result.getString("description"));
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public boolean addDimensionCourse(int courseId, int dimensionId) throws SQLException {
        this.connectDatabase();
        String addDimension = "INSERT INTO db_ite1.course_dimension "
                + "VALUES (?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addDimension)) {
            statement.setInt(1, courseId);
            statement.setInt(2, dimensionId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Dimension getDimensionDetail(int dimensionId) throws SQLException {
        this.connectDatabase();
        String getDimensionId = "SELECT d.id,dt.dimension_type_name,d.name,d.description FROM db_ite1.dimension d INNER JOIN dimension_type dt "
                + "on d.type_id = dt.id "
                + "WHERE d.id = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(getDimensionId)) {
            statement.setInt(1, dimensionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Dimension(result.getInt("id"),
                        result.getString("dimension_type_name"),
                        result.getString("name"),
                        result.getString("description"));
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public boolean updateSubjectDimension(int typeId, String name, String description, int dimensionId) throws SQLException {
        this.connectDatabase();
        String addDimension = "UPDATE db_ite1.dimension SET type_id = ?,name= ?,description= ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(addDimension)) {
            statement.setInt(1, typeId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setInt(4, dimensionId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean addDimensionType(String type) throws SQLException {
        this.connectDatabase();
        String addDimension = "INSERT INTO db_ite1.dimension_type (dimension_type_name) "
                + "VALUES (?)";
        try (PreparedStatement statement = this.connection.prepareStatement(addDimension)) {
            statement.setString(1, type);
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }
    }

    public DimensionType getDimensionTypeDetail(String type) throws SQLException {
        this.connectDatabase();
        String getDimensionType = "SELECT * FROM db_ite1.dimension_type WHERE dimension_type_name = ?";

        try (PreparedStatement statement = this.connection.prepareStatement(getDimensionType)) {
            statement.setString(1, type);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new DimensionType(result.getInt("id"),
                        result.getString("dimension_type_name"));
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public List<DimensionType> getAllDimenstionType() throws SQLException {
        this.connectDatabase();
        String getAllDimenstionType = "SELECT * FROM db_ite1.dimension_type";
        List<DimensionType> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAllDimenstionType)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new DimensionType(result.getInt("id"),
                        result.getString("dimension_type_name")));
            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }

    public HashMap<Integer, String> getOwners() throws SQLException {
        this.connectDatabase();

        HashMap<Integer, String> owners = new HashMap<>();
        String getOwners = "SELECT user.id, user.full_name FROM user JOIN account"
                + " ON user.email = account.user_email WHERE account.role_id = 1;";
        try (PreparedStatement statement = this.connection.prepareStatement(getOwners)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                owners.put(result.getInt("id"), result.getString("full_name"));
            }
            return owners;
        } finally {
            this.disconnectDatabase();
        }
    }

//    Subject part
    public Course getSubject(int id) throws SQLException {
        this.connectDatabase();
        String getCourse = "SELECT c.id, c.thumbnail, c.title, c.description, ca.category_name, c.feature, c.status_id, c.owner "
                + "FROM db_ite1.course c "
                + "JOIN db_ite1.category ca "
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
                        result.getInt("status_id") == 0 ? CourseStatus.UNPUBLISHED : CourseStatus.PUBLISHED,
                        result.getString("category_name"),
                        result.getBoolean("feature"));
            }

        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public boolean updateSubjectInformation(String courseName, String description, int owner, int status_id, int category_id, int feature, int id) throws SQLException {
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

    public boolean updateSubjectInformation(String courseName, String description, int category_id, int feature, int id) throws SQLException {
        this.connectDatabase();

        String updateUserInformation = "UPDATE db_ite1.course "
                + "SET course.title = ?, "
                + "course.description = ?, "
                + "course.category_id = ?, "
                + "course.feature = ? "
                + "WHERE course.id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateUserInformation)) {
            statement.setString(1, courseName);
            statement.setString(2, description);
            statement.setInt(3, category_id);
            statement.setInt(4, feature);
            statement.setInt(5, id);
            statement.executeUpdate();
            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } finally {
            this.disconnectDatabase();
        }

    }

    public HashMap<Integer, String> getCourses() throws SQLException {
        this.connectDatabase();

        HashMap<Integer, String> courses = new HashMap<>();
        String getHmCourse = "SELECT id, title FROM course ORDER BY title";
        try (PreparedStatement statement = this.connection.prepareStatement(getHmCourse)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                courses.put(result.getInt("id"), result.getString("title"));

            }
            return courses;
        } finally {
            this.disconnectDatabase();
        }
    }

    public ArrayList<ArrayList<String>> getSubjectList(String keyword, int categoryId, Status status, int teacherId) throws SQLException {
        this.connectDatabase();

        String option = "WHERE 1=1 ";

        if (keyword != null && !keyword.equals("")) {
            keyword = "%" + keyword + "%";
            option += "AND c.title LIKE '" + keyword + "' ";
        }

        if (categoryId != -1) {
            option += "AND c.category_id = " + categoryId + " ";
        }

        if (status != null) {
            option += "AND c.status_id = " + Status.valueOf(status) + " ";
        }

        if (teacherId != -1) {
            option += "AND c.owner = " + teacherId + " ";
        }

        String getSubject = "SELECT c.id, "
                + "       c.title, "
                + "       c2.category_name, "
                + "       IF(COUNT(l.course_id) IS NULL, 0, COUNT(l.course_id)) AS lessonCount, "
                + "       u.full_name, "
                + "       c.status_id "
                + "FROM course c "
                + "         LEFT OUTER JOIN category c2 on c.category_id = c2.id "
                + "         LEFT OUTER JOIN lesson l on c.id = l.course_id "
                + "         LEFT OUTER JOIN user u on c.owner = u.id "
                + option
                + "GROUP BY c.id, c.title, c.category_id, c2.category_name, c.owner, u.full_name, c.status_id "
                + "ORDER BY c.id";

        try (PreparedStatement statement = this.connection.prepareStatement(getSubject)) {
            ResultSet result = statement.executeQuery();
            ArrayList<ArrayList<String>> subjectInfo = new ArrayList<>();

            while (result.next()) {
                ArrayList<String> info = new ArrayList<>();

                info.add(result.getString("id"));
                info.add(result.getString("title"));
                info.add(result.getString("category_name"));
                info.add(result.getString("lessonCount"));
                info.add(result.getString("full_name"));

                Status statusInfo = result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE;
                info.add(statusInfo.toString());

                subjectInfo.add(info);
            }

            return subjectInfo;
        } finally {
            this.disconnectDatabase();
        }
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

    public Course getCategoryByCourseId(int courseId) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT id,category_id FROM db_ite1.course WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Course(
                        result.getInt("id"),
                        result.getString("category_id")
                );

            }

        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public Account getRoleByUserEmail(String email) throws SQLException {
        this.connectDatabase();
        String getRoleByUserEmail = "SELECT a.* FROM db_ite1.account a "
                + "INNER JOIN db_ite1.user u "
                + "ON a.user_email = u.email "
                + "WHERE u.email = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getRoleByUserEmail)) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = null;
                switch (result.getInt("role_id")) {
                    case 0:
                        role = Role.ADMIN;
                        break;
                    case 1:
                        role = Role.TEACHER;
                        break;
                    default:
                        role = Role.STUDENT;
                        break;
                }
                return new Account(result.getString("user_email"),
                        result.getString("password"),
                        role);
            }

        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    //Dashboard
    public int countingTotalCourse(int date) throws SQLException {
        this.connectDatabase();
        String countingCourseList = "SELECT COUNT(*) AS count FROM db_ite1.course c where DATE_FORMAT(c.created_time, \"%y-%m-%d\") "
                + "NOT BETWEEN  DATE_SUB(CURDATE(), interval ? day) "
                + "AND CURDATE();";
        try (PreparedStatement statement = this.connection.prepareStatement(countingCourseList)) {
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

    public int countingNewCourse() throws SQLException {
        this.connectDatabase();
        String countingCourseList = " SELECT COUNT(*) AS count FROM db_ite1.course c "
                + "where DATE_FORMAT(c.created_time, \"%y-%m-%d\") >= DATE(NOW()) - INTERVAL 6 DAY "
                + "AND DATE_FORMAT(c.created_time, \"%y-%m-%d\") <= DATE(NOW()) - INTERVAL 0 DAY";
        try (PreparedStatement statement = this.connection.prepareStatement(countingCourseList)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("count");
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    //my-course
    public List<Course> getMyCourse(int userId) throws SQLException {
        this.connectDatabase();
        String getMyCourse = "SELECT c.id, c.thumbnail, c.title, c.description FROM db_ite1.user_course uc "
                + "inner join db_ite1.user u "
                + "on uc.user_id = u.id "
                + "inner join db_ite1.course c "
                + "on c.id = uc.course_id "
                + "where u.id = ? and uc.registration_status = 2";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getMyCourse)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description")
                ));

            }
            return list;
        } finally {
            this.disconnectDatabase();
        }

    }
    
     public List<Course> getMyCourseSuccess(int userId) throws SQLException {
        this.connectDatabase();
        String getMyCourseSuccess = "SELECT c.id, c.thumbnail, c.title, c.description FROM db_ite1.user_course uc "
                + "inner join db_ite1.user u "
                + "on uc.user_id = u.id "
                + "inner join db_ite1.course c "
                + "on c.id = uc.course_id "
                + "where u.id = ? and uc.registration_status = 1";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getMyCourseSuccess)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description")
                ));

            }
            return list;
        } finally {
            this.disconnectDatabase();
        }

    }

    public Course getCourseNameLessonList(int courseId) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course  where id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Course(
                        result.getInt("id"),
                        result.getString("thumbnail"),
                        result.getString("title"),
                        result.getString("description"),
                        0,
                        result.getString("tag")
                );

            }

        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public boolean registerCourse(int userId, int courseId, int pricePackageId) throws SQLException {
        this.connectDatabase();

        String addRegistration = "INSERT INTO user_course (user_id, course_id, registration_time, valid_to, registration_status) "
                + "VALUES (?, ?, ?, ?, 1)";

        try (PreparedStatement statement = this.connection.prepareStatement(addRegistration)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, pricePackageId);

            return statement.executeUpdate() > 0;
        } finally {
            this.disconnectDatabase();
        }
    }

    public String getCourseName(int courseId) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course where id = ?;";
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString(3);
            }

        } finally {
            this.disconnectDatabase();
        }
        return "Khong get duoc ten khoa hoc vi bi mat session";
    }
    
    //search-from-home
   public List<Course> searchHome(String searchName) throws SQLException {
        this.connectDatabase();
        String searchCourse = "SELECT * FROM db_ite1.course WHERE title LIKE ?";
        List<Course> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchCourse)) {
            statement.setString(1, "%" + searchName + "%");
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
   
   public int countingHomeSearch(String searchName) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT COUNT(*) AS count FROM db_ite1.course "
                + "where title LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, "%" + searchName + "%");
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
        CourseRepository repo = new CourseRepository();
        try {
            List<Course> list = repo.searchHome("a");
            int list1 = repo.countingHomeSearch("a");
            System.out.println(list1);
            for (Course o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }

}
