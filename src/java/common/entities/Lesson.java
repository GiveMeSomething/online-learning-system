/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class Lesson {

    private int id;
    private String lessonName;
    private int order;
    private Status status;
    private LessonType lessonType;
    private int courseId;
    private String videoLink;
    private String htmlContent;
    private int quizId;

    public Lesson(int id, String lessonName, int order, Status status, LessonType lessonType, int courseId, String videoLink, String htmlContent, int quizId) {
        this.id = id;
        this.lessonName = lessonName;
        this.order = order;
        this.status = status;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

}
