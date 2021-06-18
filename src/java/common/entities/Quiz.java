/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package common.entities;

import java.util.List;
//my quiz
public class Quiz {

    private int id;
    private String quizName;
    private int subject_id;
    private Level quizLevel;
    private int duration;
    private int passRate;
    private TestType quizType;
    private String description;
    private List<Question> question;

    public Quiz(int id, String quizName, int subject_id, Level quizLevel, int duration, int passRate, TestType quizType, String description, List<Question> question) {
        this.id = id;
        this.quizName = quizName;
        this.subject_id = subject_id;
        this.quizLevel = quizLevel;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = quizType;
        this.description = description;
        this.question = question;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public Level getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel(Level quizLevel) {
        this.quizLevel = quizLevel;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPassRate() {
        return passRate;
    }

    public void setPassRate(int passRate) {
        this.passRate = passRate;
    }

    public TestType getQuizType() {
        return quizType;
    }

    public void setQuizType(TestType quizType) {
        this.quizType = quizType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", quizName=" + quizName + ", subject_id=" + subject_id + ", quizLevel=" + quizLevel + ", duration=" + duration + ", passRate=" + passRate + ", quizType=" + quizType + ", description=" + description + '}';
    }

}
