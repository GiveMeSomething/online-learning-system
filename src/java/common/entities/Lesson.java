/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package common.entities;

public class Lesson {

    private int id;
    // Change lessonName to name for ajax: Anhvd
    private String name;
    private int order;
    private Status status;
    private LessonType lessonType;
    private int courseId;
    private String videoLink;
    private String htmlContent;
    private int quizId;

    // Add default constructor: Anhvd
    public Lesson() {
    }

    public Lesson(int id, String lessonName, int order, Status status,
            LessonType lessonType, int courseId, String videoLink, String htmlContent, int quizId) {
        this.id = id;
        this.name = lessonName;
        this.order = order;
        this.status = status;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
        this.quizId = quizId;
    }

    public Lesson(int id, String lessonName) {
        this.id = id;
        this.name = lessonName;
    }

    public Lesson(String lessonName, int order, LessonType lessonType, int courseId) {
        this.name = lessonName;
        this.order = order;
        this.lessonType = lessonType;
        this.courseId = courseId;
    }

    public Lesson(String lessonName) {
        this.name = lessonName;
    }

    public Lesson(String lessonName, int order,
            LessonType lessonType, int courseId, String videoLink, String htmlContent) {
        this.name = lessonName;
        this.order = order;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
    }

    public Lesson(String lessonName, int order, LessonType lessonType,
            int courseId, String htmlContent, int quizId) {
        this.name = lessonName;
        this.order = order;
        this.lessonType = lessonType;
        this.courseId = courseId;
        this.htmlContent = htmlContent;
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
