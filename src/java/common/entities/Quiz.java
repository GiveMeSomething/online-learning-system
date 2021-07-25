/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package common.entities;

//my quiz

public class Quiz {

    private int id;
    private String quizName;
    private int subjectId;
    private String subjectName;
    private Level level;
    private int duration;
    private TestType quizType;
    private float passRate;
    private String description;
    private int questionNum;

    public Quiz(int id) {
        this.id = id;
    }

    public Quiz(int id, String name, int subjectId, String subjectName, Level level, int duration, float passRate, TestType testType, String description, int questionNum) {
        this.id = id;
        this.quizName = name;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.level = level;
        this.duration = duration;
        this.passRate = passRate;
        this.quizType = testType;
        this.description = description;
        this.questionNum = questionNum;
    }

    public Quiz(int id, String quizName, int subjectId, String subjectName, Level level, int duration, TestType quizType, float passRate, String description) {
        this.id = id;
        this.quizName = quizName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.level = level;
        this.duration = duration;
        this.quizType = quizType;
        this.passRate = passRate;
        this.description = description;
    }

    public Quiz(int id, String quizName, int subjectId, Level level, int duration, TestType quizType, float passRate, String description) {
        this.id = id;
        this.quizName = quizName;
        this.subjectId = subjectId;
        this.level = level;
        this.duration = duration;
        this.quizType = quizType;
        this.passRate = passRate;
        this.description = description;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Level getLevel() {
        return level;
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

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

}
