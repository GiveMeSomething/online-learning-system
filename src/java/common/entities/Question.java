/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class Question {

    private int id;
    private String course;
    private String lesson_name;
    private String dimension_name;
    private Status status;
    private String content;
    private String level;
    private String media;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String explaination;

    public Question() {
    }

    public Question(int id, String course, String lesson_name, String dimension_name, Status status, String content, String level, String media, String option1, String option2, String option3, String option4, String answer, String explaination) {
        this.id = id;
        this.course = course;
        this.lesson_name = lesson_name;
        this.dimension_name = dimension_name;
        this.status = status;
        this.content = content;
        this.level = level;
        this.media = media;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explaination = explaination;
    }

    public Question(int id, String course, String lesson_name, String dimension_name, Status status, String content, String media, String option1, String option2, String option3, String option4, String answer, String explaination) {
        this.id = id;
        this.course = course;
        this.lesson_name = lesson_name;
        this.dimension_name = dimension_name;
        this.status = status;
        this.content = content;
        this.media = media;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explaination = explaination;
    }

    public Question(int id, String course, String lesson_name, String dimension_name, Status status, String content, String level) {
        this.id = id;
        this.course = course;
        this.lesson_name = lesson_name;
        this.dimension_name = dimension_name;
        this.status = status;
        this.content = content;
        this.level = level;
    }
    
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getDimension_name() {
        return dimension_name;
    }

    public void setDimension_name(String dimension_name) {
        this.dimension_name = dimension_name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", course=" + course + ", lesson_name=" + lesson_name + ", dimension_name=" + dimension_name + ", status=" + status + ", content=" + content + ", level=" + level + ", media=" + media + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer + ", explaination=" + explaination + '}';
    }

    

}
