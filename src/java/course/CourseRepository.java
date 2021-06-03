/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import common.entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.Repository;

public class CourseRepository extends Repository {

    public Course getCourse(int id) throws Exception {
        this.connectDatabase();
        String getCourse = "SELECT c.id, c.thumbnail, c.title, c.description, c.tag, ca.category_name, MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "ON c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "ON p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
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
    
    public static void main(String[] args) throws Exception {
        CourseRepository repo = new CourseRepository();
        try {
            Course a = repo.getCourse(4);
           
                System.out.println(a);
            
        } catch (Exception e) {
        }

    }

}
