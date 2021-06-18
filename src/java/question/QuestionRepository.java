/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Course;
import common.entities.Question;
import common.entities.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import common.utilities.Repository;

public class QuestionRepository extends Repository {

    public Question getQuestionDetails(int questionId) throws SQLException {
        this.connectDatabase();
        String getQuestionDetails = "SELECT q.id,c.title,l.lesson_name,d.name AS dimension_name,q.status_id,q.content,q.media,q.option1,q.option2,q.option3,q.option4,q.answer,q.explaination "
                + "FROM db_ite1.questions_bank q "
                + "INNER JOIN db_ite1.question_course_dim_les qc "
                + "ON q.id = qc.question_id "
                + "INNER JOIN db_ite1.course c "
                + "ON qc.course_id= c.id "
                + "INNER JOIN dimension d "
                + "ON qc.dimension_id = d.id "
                + "INNER JOIN lesson l "
                + "ON qc.lesson_id = l.id WHERE q.id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getQuestionDetails)) {
            statement.setInt(1, questionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Question(result.getInt("id"),
                        result.getString("title"),
                        result.getString("lesson_name"),
                        result.getString("dimension_name"),
                        result.getInt("status_id") == 1 ? Status.ACTIVE : Status.INACTIVE,
                        result.getString("content"),
                        result.getString("media"),
                        result.getString("option1"),
                        result.getString("option2"),
                        result.getString("option3"),
                        result.getString("option4"),
                        result.getString("answer"),
                        result.getString("explaination"));
            }
        } finally {
            this.disconnectDatabase();
        }
        return null;
    }

    public Question getAnswerOptionsByQuestionId(int questionId) throws SQLException {
        this.connectDatabase();
        String getAnswerOptionsByQuestionId = "SELECT option1,option2,option3,option4 FROM db_ite1.questions_bank WHERE id = ?";

        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAnswerOptionsByQuestionId)) {
            statement.setInt(1, questionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Question(
                        result.getString("option1"),
                        result.getString("option2"),
                        result.getString("option3"),
                        result.getString("option4")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void deleteAnswerOptions(String column,int questionId) throws SQLException {
        this.connectDatabase();
        String deleteAnswerOptions = "UPDATE db_ite1.questions_bank SET "+column+" = '' "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(deleteAnswerOptions)) {
            statement.setInt(1, questionId);
            statement.executeUpdate();
        } finally {
            this.disconnectDatabase();
        }
    }
    
    public Question getAnswerDetail(String column,int questionId) throws SQLException{
        this.connectDatabase();
        String getAnswerDetail = "SELECT "+column+" FROM db_ite1.questions_bank WHERE id = ?";

        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAnswerDetail)) {
            statement.setInt(1, questionId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Question(
                    column.equals("option1") ? result.getString("option1") : "",
                    column.equals("option2") ? result.getString("option2") : "",
                    column.equals("option3") ? result.getString("option3") : "",
                    column.equals("option4") ? result.getString("option4") : ""
                );
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void updateAnswerOptions(String column,String content,int questionId) throws SQLException {
        this.connectDatabase();
        String deleteAnswerOptions = "UPDATE db_ite1.questions_bank SET "+column+" = ? "
                + "WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(deleteAnswerOptions)) {
            statement.setString(1, content);
            statement.setInt(2, questionId);
            statement.executeUpdate();
        } finally {
            this.disconnectDatabase();
        }
    }
    

    public static void main(String[] args) {
        QuestionRepository repo = new QuestionRepository();
        try {
            Question question = repo.getAnswerDetail("option3",2);
            System.out.println(question);
        } catch (Exception e) {
        }
    }
}
