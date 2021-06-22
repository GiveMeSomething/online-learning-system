/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Level;
import common.entities.Question;
import common.entities.Status;
import java.util.List;

public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService() {
        this.questionRepository = new QuestionRepository();
    }

    public List<Question> getQuestionsWithCondition(int courseId, String keyword, Level level, Status status, String dimensionName) {
        try {
            List<Question> result = questionRepository.getQuestionsWithCondition(courseId, keyword, level, status, dimensionName);

            if (result == null || result.size() == 0) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Question> getDimensionList(int courseId) {
        try {
            List<Question> result = questionRepository.getDimensionList(courseId);

            if (result == null || result.size() == 0) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
