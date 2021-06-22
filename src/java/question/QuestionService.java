/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

public class QuestionService {

    private final QuestionRepository qr;

    public QuestionService() {
        this.qr = new QuestionRepository();
    }

    public boolean addQuestion(int status, String content, String media, String explaination, String answer, String option1,
            String option2, String option3, String option4) {
        try {
            return qr.addQuestion(status, content, media, explaination, answer, option1, option2, option3, option4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
