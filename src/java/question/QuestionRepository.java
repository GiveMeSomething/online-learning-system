/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Level;
import common.entities.Question;
import common.entities.Status;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository extends Repository {

    public List<Question> getQuestionsWithCondition(int courseId, String keyword, Level level, Status status, String dimensionName) throws SQLException {
        this.connectDatabase();
        String condition = "where 1=1 AND c.id = ? ";

        if (keyword != null && !keyword.equals("")) {
            keyword = "%" + keyword + "%";
            condition += "AND d.name LIKE '" + keyword + "' ";
        }

        if (level != null) {
            condition += "AND q.level_id = " + Level.valueOf(level) + " ";
        }

        if (status != null) {
            condition += "AND q.status_id = " + Status.valueOf(status) + " ";

        }
        if (dimensionName != null) {
            condition += "AND d.name LIKE '%" + dimensionName + "%' ";
        }

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
                + condition
                + "ORDER by q.id asc ";

        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
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

    public List<Question> getDimensionList(int courseId) throws SQLException {
        this.connectDatabase();
        String searchQuestion = "SELECT d.name "
                + "FROM db_ite1.dimension d "
                + "INNER JOIN db_ite1.question_course_dim_les qcdl "
                + "on qcdl.dimension_id = d.id "
                + "INNER JOIN db_ite1.course c "
                + "on qcdl.course_id = c.id "
                + "where c.id = ? "
                + "GROUP BY d.id";

        List<Question> list = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(searchQuestion)) {
            statement.setInt(1, courseId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(new Question(
                        result.getString("name")
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
//            List<Question> list = repo.getQuestionsWithCondition(2, "", "", "", "");
//            int list = repo.countTotalQuestionWithCondition(2, "AND (ql.type='HARD')");
//            System.out.println(list);
//            for (Question o : list) {
//                System.out.println(o);
//            }
        } catch (Exception e) {
        }
    }
}
