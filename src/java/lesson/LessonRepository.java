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

    public ArrayList<Lesson> getLessonList(int subjectId) throws SQLException {
        this.connectDatabase();

        String getLessons = "SELECT id, lesson_name, `order`,  "
                + "       status_id, type_id, course_id,  "
                + "       video_link, html_content, quiz_id "
                + "FROM lesson "
                + "WHERE course_id = ?";
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
}
