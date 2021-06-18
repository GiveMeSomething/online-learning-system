/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Quiz;
import java.sql.SQLException;

public class QuizService {

    final private QuizRepository quizRepository;

    public QuizService() {
        this.quizRepository = new QuizRepository();
    }

    public boolean addQuizOverView(Quiz quiz) {
        try {
            return quizRepository.addQuizOverView(quiz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateQuizOverView(Quiz quiz){
        try {
            return quizRepository.updateQuizOverView(quiz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
