/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;
import common.entities.Question;
import common.entities.Status;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionRepository extends Repository {

    public boolean addQuestion(int status, String content, String media, String explaination, String answer, String option1,
            String option2, String option3, String option4) throws SQLException {
        this.connectDatabase();
        String query = "INSERT INTO `db_ite1`.`questions_bank` (`status_id`, `content`, `media`, `explaination`, `answer`, `option1`, `option2`, `option3`, `option4`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, status);
            statement.setString(2, content);
            statement.setString(3, media);
            statement.setString(4, explaination);
            statement.setString(5, answer);
            statement.setString(6, option1);
            statement.setString(7, option2);
            statement.setString(8, option3);
            statement.setString(9, option4);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } finally {
            this.disconnectDatabase();
        }
        return false;
    }

    public List<Question> getQuestions(int courseId, int page) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT q.id ,q.status_id, q.content, c.title, d.name as 'dimension_name', l.lesson_name,ql.type, s.value as 'status' "
                + "FROM db_ite1.questions_bank q "
                + "INNER JOIN db_ite1.question_course_dim_les qcdl "
                + "on q.id = qcdl.question_id "
                + "INNER JOIN db_ite1.course c "
                + "on qcdl.course_id = c.id "
                + "INNER JOIN db_ite1.quiz_level ql "
                + "on q.level_id = ql.id "
                + "INNER JOIN db_ite1.dimension d "
                + "on qcdl.dimension_id = d.id "
                + "INNER JOIN db_ite1.lesson l "
                + "on qcdl.lesson_id = l.id "
                + "INNER JOIN db_ite1.status s "
                + "on q.status_id = s.id "
                + "where c.id = ? "
                + "ORDER by q.id asc "
                + "LIMIT 5 OFFSET ?";

        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setInt(2, (page - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Question(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("lesson_name"),
                        result.getString("dimension_name"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("content"),
                        result.getString("type")
                ));

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countTotalQuestion(int courseId) throws SQLException {
        this.connectDatabase();
        String countTotalQuestion = "SELECT count(*) "
                + "FROM db_ite1.questions_bank q "
                + "INNER JOIN db_ite1.question_course_dim_les qcdl "
                + "on q.id = qcdl.question_id "
                + "INNER JOIN db_ite1.course c "
                + "on qcdl.course_id = c.id "
                + "INNER JOIN db_ite1.dimension d "
                + "on qcdl.dimension_id = d.id "
                + "INNER JOIN db_ite1.lesson l "
                + "on qcdl.dimension_id = l.id "
                + "INNER JOIN db_ite1.status s "
                + "on q.status_id = s.id "
                + "where c.id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(countTotalQuestion)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }
        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public int countingQuestionListSearch(int courseId, String searchName) throws SQLException {
        this.connectDatabase();
        String sql = "SELECT count(c.id) "
                + "FROM db_ite1.question_course_dim_les qcdl "
                + "INNER JOIN db_ite1.questions_bank q "
                + "on q.id = qcdl.question_id "
                + "INNER JOIN db_ite1.course c "
                + "on c.id = qcdl.course_id "
                + "where c.id = ? AND q.content LIKE ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setString(2, "%" + searchName + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt(1);
            }

        } finally {
            this.disconnectDatabase();
        }
        return 0;
    }

    public List<Question> searchQuestion(int courseId, String searchName, int page) throws SQLException {
        this.connectDatabase();
        String searchQuestion = "SELECT q.id ,q.status_id, q.content, c.title, d.name as 'dimension_name', l.lesson_name,ql.type, s.value as 'status' "
                + "FROM db_ite1.questions_bank q "
                + "INNER JOIN db_ite1.question_course_dim_les qcdl "
                + "on q.id = qcdl.question_id "
                + "INNER JOIN db_ite1.course c "
                + "on qcdl.course_id = c.id "
                + "INNER JOIN db_ite1.quiz_level ql "
                + "on q.level_id = ql.id "
                + "INNER JOIN db_ite1.dimension d "
                + "on qcdl.dimension_id = d.id "
                + "INNER JOIN db_ite1.lesson l "
                + "on qcdl.lesson_id = l.id "
                + "INNER JOIN db_ite1.status s "
                + "on q.status_id = s.id "
                + "where c.id = ? AND d.name LIKE ? "
                + "ORDER by q.id asc LIMIT 5 OFFSET ?";
   
        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchQuestion)) {
            statement.setInt(1, courseId);
            statement.setString(2, "%" + searchName + "%");
            statement.setInt(3, (page - 1) * 5);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Question(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("lesson_name"),
                        result.getString("dimension_name"),
                        result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                        result.getString("content"),
                        result.getString("type")
                ));

            }
            return list;
        } finally {
            this.disconnectDatabase();
        }
    }

    public static void main(String[] args) throws Exception {
        QuestionRepository repo = new QuestionRepository();
        try {
            List<Question> list = repo.searchQuestion(63, "Which", 2);
//            int list = repo.countingQuestionListSearch(10, "which");
            System.out.println(list);
            for (Question o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }
}
