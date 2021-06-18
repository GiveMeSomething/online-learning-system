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
    private Level level;
    private int duration;
    private float passRate;
    private TestType testType;
    private String description;

    public Quiz(int id, String name, int subjectId, Level level, int duration, float passRate, TestType testType, String description) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.level = level;
        this.duration = duration;
        this.passRate = passRate;
        this.testType = testType;
        this.description = description;
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

}
