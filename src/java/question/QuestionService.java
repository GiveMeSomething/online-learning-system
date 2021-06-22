/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Question;
import java.util.List;

public class QuestionService {
   private QuestionRepository questionRepository;

    public QuestionService() {
        this.questionRepository = new QuestionRepository();
    }
    
    public List<Question> getQuestions(int courseId, int page) {
        try {
            List<Question> result = questionRepository.getQuestions(courseId, page);

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
    
    public int countTotalQuestion(int courseId) {

        try {
            return questionRepository.countTotalQuestion(courseId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public boolean addQuestion(int status, String content, String media, String explaination, String answer, String option1,
            String option2, String option3, String option4) {
        try {
            return questionRepository.addQuestion(status, content, media, explaination, answer, option1, option2, option3, option4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
     public int countingQuestionListSearch(int courseId, String searchName) {
        try {
            return questionRepository.countingQuestionListSearch(courseId, searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public List<Question> searchQuestion(int cateID, String searchName, int page) {
        try {
            return questionRepository.searchQuestion(cateID, searchName, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

}
