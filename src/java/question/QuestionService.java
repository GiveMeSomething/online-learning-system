/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */

package question;

import common.entities.Question;
import java.util.List;


public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService() {
        questionRepository = new QuestionRepository();
    }
    
    public Question getQuestionDetails(int questionId) {
        try {
            return questionRepository.getQuestionDetails(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Question getAnswerOptionsByQuestionId(int questionId){
        try {
            return questionRepository.getAnswerOptionsByQuestionId(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteAnswerOptions(String column,int questionId){
        try {
            questionRepository.deleteAnswerOptions(column, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Question getAnswerDetail(String column,int questionId){
        try {
            return questionRepository.getAnswerDetail(column,questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateAnswerOptions(String column,String content,int questionId){
        try {
            questionRepository.updateAnswerOptions(column, content, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
