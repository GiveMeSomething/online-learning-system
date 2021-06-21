/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class Quiz {

    private int id;
    private String name;
    private int subjectId;
    private String subjectName;
    private Level level;
    private int duration;
    private float passRate;
    private TestType testType;
    private String description;
    private int questionNum;

    public Quiz(int id, String name, int subjectId, String subjectName, Level level, int duration, float passRate, TestType testType, String description, int questionNum) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.level = level;
        this.duration = duration;
        this.passRate = passRate;
        this.testType = testType;
        this.description = description;
        this.questionNum = questionNum;
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

    public void setLevel(Level level) {
        this.level = level;
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

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
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
