/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;


import common.entities.Level;
import common.entities.Status;

import common.entities.Lesson;
import common.entities.Question;
import java.util.List;


public class QuestionService {
    private final QuestionRepository questionRepository;


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
    
    public boolean updateAnswerOptions(String column,String content,int questionId){
        try {
           return questionRepository.updateAnswerOptions(column, content, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Lesson> getLessonByCourseId(int courseId){
        try {
           return questionRepository.getLessonByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateQuestionBankByQuestionId(int statusId,String content,String media,
            String option1,String option2,String option3,String option4,String option5,String explaination,
            String answer,int questionId) {
        try {
          return questionRepository.updateQuestionBankByQuestionId(statusId, content, media,
                   option1, option2, option3, option4, option5, explaination,answer, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateQuestionCourseDimLes(int courseId,int dimensionId,int lessonId,int questionId){
        try {
          return questionRepository.updateQuestionCourseDimLes(courseId, dimensionId, lessonId, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Question getAnswerByQuestionId(int questionId){
        try {
            return questionRepository.getAnswerByQuestionId(questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addAnswer(String column,String content,int questionId){
        try {
          return questionRepository.addAnswer(column, content, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int countAnswerOptions() {
        try {
          return questionRepository.countAnswerOptions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
