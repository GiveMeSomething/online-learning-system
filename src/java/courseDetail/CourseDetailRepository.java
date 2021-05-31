/**
 *
 * @author Dinh Kong Thanh
 */
package courseDetail;

import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.Repository;

public class CourseDetailRepository extends Repository {

    public Course getCourse(int id) throws Exception {
        this.connectDatabase();
        String sql = "select c.id,c.thumbnail,c.title,c.description,c.tag,ca.category_name,MIN(pp.list_price) "
                + "AS price from db_ite1_updated.course c "
                + "INNER JOIN db_ite1_updated.course_package p "
                + "on c.id = p.course_id "
                + "INNER JOIN db_ite1_updated.price_package pp "
                + "on p.package_id = pp.id "
                + "INNER JOIN db_ite1_updated.category ca "
                + "on ca.id = c.category_id "
                + "where c.id = ? "
                + "GROUP BY c.id ";

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
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

        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        CourseDetailRepository repo = new CourseDetailRepository();
        try {
            Course a = repo.getCourse(4);
           
                System.out.println(a);
            
        } catch (Exception e) {
        }

    }

}
