/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Quiz;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizService {

    private final QuizRepository quizRepository;

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

    public boolean updateQuizOverView(Quiz quiz) {
        try {
            return quizRepository.updateQuizOverView(quiz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Quiz> getQuizList(int subjectId) {
        try {
            ArrayList<Quiz> result = quizRepository.getQuizList(subjectId);
            if (result.size() <= 0) {
                return null;
            } else {
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
