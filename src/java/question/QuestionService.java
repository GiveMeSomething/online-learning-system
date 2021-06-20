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
    
    public boolean deleteAnswerOptions(String column,int questionId){
        try {
           return questionRepository.deleteAnswerOptions(column, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
            String option1,String option2,String option3,String option4,String option5,String explaination,
            String answer,int questionId) {
        try {
           questionRepository.updateQuestionBankByQuestionId(statusId, content, media,
                   option1, option2, option3, option4, option5, explaination,answer, questionId);
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
    
    public void addAnswer(String column,String content,int questionId){
        try {
           questionRepository.addAnswer(column, content, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int countAnswerOptions() {
        try {
          return questionRepository.countAnswerOptions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void addColumnAnswer(String columnAdded,String previousColumn){
        try {
          questionRepository.addColumnAnswer(columnAdded, previousColumn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
