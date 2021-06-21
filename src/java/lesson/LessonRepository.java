/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package lesson;

import common.entities.Lesson;
import common.entities.LessonType;
import common.entities.Status;
import common.utilities.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LessonRepository extends Repository {

    public ArrayList<Lesson> getLessonList(int subjectId, String keyword) throws SQLException {
        this.connectDatabase();

        String option = "";

        if (keyword != null && !keyword.equals("")) {
            option += "AND lesson_name LIKE '%" + keyword + "%'";
        }

        String getLessons = "SELECT id, lesson_name, `order`,  "
                + "       status_id, type_id, course_id,  "
                + "       video_link, html_content, quiz_id "
                + "FROM lesson "
                + "WHERE course_id = ? " + option;
        ArrayList<Lesson> lessons = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getLessons)) {
            statement.setInt(1, subjectId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int lessonType = result.getInt("type_id");
                lessons.add(
                        new Lesson(
                                result.getInt("id"),
                                result.getString("lesson_name"),
                                result.getInt("order"),
                                result.getInt("status_id") == 0 ? Status.INACTIVE : Status.ACTIVE,
                                lessonType == 1
                                        ? LessonType.SUBJECT_TOPIC
                                        : (lessonType == 2 ? LessonType.LESSON : LessonType.QUIZ),
                                result.getInt("course_id"),
                                result.getString("video_link"),
                                result.getString("html_content"),
                                result.getInt("quiz_id")
                        )
                );
            }

            return lessons;
        } finally {
            this.disconnectDatabase();
        }
    }

    public boolean updateLessonStatus(int lessonId, Status status) throws SQLException {
        this.connectDatabase();

        String updateLesson = "UPDATE lesson SET status_id = ? WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(updateLesson)) {
            statement.setInt(1, Status.valueOf(status));
            statement.setInt(2, lessonId);

            boolean isUpdate = statement.executeUpdate() > 0;
            return isUpdate;
        } finally {
            this.disconnectDatabase();
        }

    }
}
