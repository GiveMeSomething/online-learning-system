/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
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
    private int quiz_id;

    public Lesson(int id, String lessonName, int order, Status status,
            LessonType lessonType, int courseId) {
        this.id = id;
        this.lessonName = lessonName;
        this.order = order;
        this.status = status;
        this.lessonType = lessonType;
        this.courseId = courseId;
    }

    public Lesson(int id, String lessonName, int order, Status status,
            LessonType lessonType, int courseId, String videoLink, String htmlContent) {
        this.id = id;
        this.lessonName = lessonName;
        this.order = order;
        this.status = status;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
    }

    public Lesson(int id, String lessonName, int order, Status status,
            LessonType lessonType, int courseId, String htmlContent, int quiz_id) {
        this.id = id;
        this.lessonName = lessonName;
        this.order = order;
        this.status = status;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.htmlContent = htmlContent;
        this.quiz_id = quiz_id;
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

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", lessonName=" + lessonName + ", order=" + order + ", status=" + status + ", lessonType=" + lessonType + ", courseId=" + courseId + ", videoLink=" + videoLink + ", htmlContent=" + htmlContent + ", quiz_id=" + quiz_id + '}';
    }

}
