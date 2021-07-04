/**
 * <<<<<<< HEAD
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 * =======
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 * >>>>>>> c44157fc169893bad2cd702c98aab9860ade848f
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

    public boolean addLessonDetail(Lesson lesson) throws SQLException {
        this.connectDatabase();

        String addLessonType1 = "INSERT INTO lesson(lesson_name, lesson.order, "
                + "status_id, type_id, course_id) VALUES(?,?,1,1,?)";
        String addLessonType2 = "INSERT INTO lesson(lesson_name, lesson.order, "
                + "status_id, type_id, course_id, video_link, html_content) VALUES(?,?,1,2,?,?,?)";
        String addLessonType3 = "INSERT INTO lesson(lesson_name, lesson.order, "
                + "status_id, type_id, course_id, html_content, quiz_id) VALUES(?,?,1,3,?,?,?)";
        switch (LessonType.valueOf(lesson.getLessonType())) {
            case 1:
                try (PreparedStatement statement = this.connection.prepareStatement(addLessonType1)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
            case 2:
                try (PreparedStatement statement = this.connection.prepareStatement(addLessonType2)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());
                    statement.setString(4, lesson.getVideoLink());
                    statement.setString(5, lesson.getHtmlContent());

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
            default:
                try (PreparedStatement statement = this.connection.prepareStatement(addLessonType3)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());
                    statement.setString(4, lesson.getHtmlContent());
                    statement.setInt(5, lesson.getQuizId());

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
        }
    }

    public boolean updateLessonDetail(Lesson lesson, int id) throws SQLException {
        this.connectDatabase();

        String updateLessonWithType1 = "UPDATE lesson SET lesson_name = ?, lesson.order = ?, "
                + "type_id = 1, course_id = ? WHERE id = ?";
        String updateLessonWithType2 = "UPDATE lesson SET lesson_name = ?, "
                + "lesson.order = ?, type_id = 2, course_id = ?, video_link = ?, "
                + "html_content = ? WHERE id = ?";
        String updateLessonWithType3 = "UPDATE lesson SET lesson_name = ?, "
                + "lesson.order = ?, type_id = 3, course_id = ?,"
                + " html_content=?, quiz_id = ? WHERE id = ?";
        switch (LessonType.valueOf(lesson.getLessonType())) {
            case 1:
                try (PreparedStatement statement = this.connection.prepareStatement(updateLessonWithType1)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());
                    statement.setInt(4, id);

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
            case 2:
                try (PreparedStatement statement = this.connection.prepareStatement(updateLessonWithType2)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());
                    statement.setString(4, lesson.getVideoLink());
                    statement.setString(5, lesson.getHtmlContent());
                    statement.setInt(6, id);

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
            default:
                try (PreparedStatement statement = this.connection.prepareStatement(updateLessonWithType3)) {
                    statement.setString(1, lesson.getName());
                    statement.setInt(2, lesson.getOrder());
                    statement.setInt(3, lesson.getCourseId());
                    statement.setString(4, lesson.getHtmlContent());
                    statement.setInt(5, lesson.getQuizId());
                    statement.setInt(6, id);

                    return statement.executeUpdate() > 0;
                } finally {
                    this.disconnectDatabase();
                }
        }
    }

    public Lesson checkLessonExist(Lesson lesson) throws SQLException {
        this.connectDatabase();

        String isLessonExist = "SELECT lesson_name FROM lesson "
                + "WHERE lesson_name = ? AND lesson.order = ? AND course_id = ? AND type_id = ?";
        try (PreparedStatement statment = this.connection.prepareStatement(isLessonExist)) {
            statment.setString(1, lesson.getName());
            statment.setInt(2, lesson.getOrder());
            statment.setInt(3, lesson.getCourseId());
            statment.setInt(4, LessonType.valueOf(lesson.getLessonType()));
            ResultSet result = statment.executeQuery();
            while (result.next()) {
                return new Lesson(result.getString("lesson_name"));
            }
            return null;
        } finally {
            this.disconnectDatabase();
        }
    }

    public Lesson getLesson(int id) throws SQLException {
        this.connectDatabase();

        String getLesson = "SELECT * FROM lesson WHERE id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getLesson)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new Lesson(result.getInt("id"),
                        result.getString("lesson_name"),
                        result.getInt("order"),
                        result.getInt("status_id") == 1 ? Status.ACTIVE : Status.INACTIVE,
                        result.getInt("type_id") == 1 ? LessonType.SUBJECT_TOPIC
                        : (result.getInt("type_id") == 2 ? LessonType.LESSON : LessonType.QUIZ),
                        result.getInt("course_id"),
                        result.getString("video_link"),
                        result.getString("html_content"), result.getInt("quiz_id"));
            }

            return null;
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
