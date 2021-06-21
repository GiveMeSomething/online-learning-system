/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Quiz;
import common.entities.TestType;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService() {
        this.quizRepository = new QuizRepository();
    }

    public ArrayList<Quiz> getQuizList(int subjectId, String keyword, TestType quizType) {
        try {
            return quizRepository.getQuizList(subjectId, keyword, quizType);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
