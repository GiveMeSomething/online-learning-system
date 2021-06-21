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

    public List<Question> getQuestionsWithCondition(int courseId, String keyword, List<Level> levels, List<Status> status, List<String> dimensionIds) throws SQLException {
        this.connectDatabase();
        String condition = "";

        if (keyword != null && !keyword.equals("")) {
            keyword = "%" + keyword + "%";
            condition += "AND d.name LIKE '" + keyword + "' ";
        }

        if (levels != null) {
            condition += "AND (1 = 0 ";
            for (Level level : levels) {
                condition += "OR q.level_id = " + Level.valueOf(level) + " ";
            }
            condition += ") ";
        }

        if (status != null) {
            condition += "AND (1 = 0 ";
            for (Status statusList : status) {
                condition += "OR q.status_id = " + Status.valueOf(statusList) + " ";
            }
            condition += ") ";
        }
        if (dimensionIds != null) {
            condition += "AND (1 = 0 ";
            for (String dimension : dimensionIds) {
                condition += "OR d.name = '" + dimension + "' ";
            }
            condition += ") ";
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
                + "where c.id = ? " + condition
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
