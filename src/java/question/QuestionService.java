/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */

package question;

import common.entities.Lesson;
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
    
    public List<Lesson> getLessonByCourseId(int courseId){
        try {
           return questionRepository.getLessonByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateQuestionBankByQuestionId(int statusId,String content,String media,
            String option1,String option2,String option3,String option4,String explaination,
            int questionId) {
        try {
           questionRepository.updateQuestionBankByQuestionId(statusId, content, media,
                   option1, option2, option3, option4, explaination, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateQuestionCourseDimLes(int courseId,int dimensionId,int lessonId,int questionId){
        try {
           questionRepository.updateQuestionCourseDimLes(courseId, dimensionId, lessonId, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Question getAnswerByQuestionId(int questionId){
        try {
            return questionRepository.getAnswerByQuestionId(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
