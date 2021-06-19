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
    private int subjectId;
    private Level quizLevel;
    private int duration;
    private TestType quizType;
    private float passRate;
    private String description;
    private List<Question> question;

    public Quiz(int id, String quizName, int subjectId, Level quizLevel, int duration, int passRate, TestType quizType, String description, List<Question> question) {
        this.id = id;
        this.quizName = quizName;
        this.subjectId = subjectId;
        this.quizLevel = quizLevel;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = quizType;
        this.description = description;
        this.question = question;
    }

    public Quiz(int id) {
        this.id = id;
    }

    public Quiz(int id, String name, int subjectId, Level level, int duration, float passRate, TestType testType, String description) {
        this.id = id;
        this.quizName = name;
        this.subjectId = subjectId;
        this.quizLevel = level;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = testType;
        this.description = description;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public float getPassRate() {
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
        return "Quiz{" + "id=" + id + ", quizName=" + quizName + ", subject_id=" + subjectId + ", quizLevel=" + quizLevel + ", duration=" + duration + ", passRate=" + passRate + ", quizType=" + quizType + ", description=" + description + '}';
    }

}
