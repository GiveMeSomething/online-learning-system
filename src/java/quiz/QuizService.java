/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package quiz;

import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.Lesson;
import common.entities.Question;
import common.entities.Quiz;
import common.entities.TestType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService() {
        this.quizRepository = new QuizRepository();
    }

    public boolean isFinishQuiz(String userId, String quizid) {
        try {
            return quizRepository.isFinishQuiz(userId, quizid);
        } catch (Exception e) {
        }
        return false;
    }

    public int getQuizIdTheoYeuCauCuaDuyAnh(int lessonId) {
        try {
            return quizRepository.getQuizIdTheoYeuCauCuaDuyAnh(lessonId);
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Quiz> getQuizList(int subjectId, String keyword, TestType quizType) {
        try {
            return quizRepository.getQuizList(subjectId, keyword, quizType);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    public Quiz getExistQuiz(Quiz quiz) {
        try {
            return quizRepository.getExistQuiz(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Quiz getQuiz(int id) {
        try {
            return quizRepository.getQuiz(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Question> getQuestionByDimension(int courseId, int dimensionId, int lessonId, int level, int num) {
        try {
            return quizRepository.getQuestion(courseId, dimensionId, lessonId, level, num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DimensionType getQuizDimension(Quiz quiz) {
        try {
            return quizRepository.getQuizDimension(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, String> getDimensionIDByQuizID(Quiz quiz) {
        try {
            return quizRepository.getDimensionIDByQuizID(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDimensionTypeForEdit(int dimId) {
        try {
            return quizRepository.getDimensionTypeForEdit(dimId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countQuestion(Quiz quiz) {
        try {
            return quizRepository.countQuestion(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Dimension> getDimension(int courseId, int dimensionType) {
        try {
            return quizRepository.getDimensionByType(courseId, dimensionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Lesson> getTopic(int courseId) {
        try {
            return quizRepository.getTopic(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addQuizSetting(Quiz quiz, int quesId) {
        try {
            return quizRepository.addQuizSetting(quiz, quesId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Integer> getQuizSetting(int quizId, int dimensionId, int lessonId) {
        try {
            return quizRepository.getQuizSetting(quizId, dimensionId, lessonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateQuizSetting(int quizId, int dimensionId, int lessonId, int numberOfQuestion) {
        try {
            return quizRepository.updateQuizSetting(quizId, dimensionId, lessonId, numberOfQuestion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashMap<Integer, ArrayList<Integer>> getDataForQuestion(int quizId) {
        try {
            return quizRepository.getDataForQuestion(quizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addNewQuizSetting(Quiz quiz, int dimensionId, int lessonId, int numberOfQuestion) {
        try {
            return quizRepository.addNewQuizSetting(quiz, dimensionId, lessonId, numberOfQuestion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashMap<Integer, String> getQuizForLesson(int courseId) {
        try {
            return quizRepository.getQuizForLesson(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getQuizReview(int quizId, int userId) {
        try {
            return quizRepository.getQuizReview(quizId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean getAnswerFromUser(int userQuizId, String userChoice,
            int quesitionId, boolean questionStatus) {
        try {
            return quizRepository.getAnswerFromUser(userQuizId, userChoice, quesitionId, questionStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Object> getUserQuiz(int userId, int quizId) {
        try {
            return quizRepository.getUserQuiz(userId, quizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUserQuiz(int userId, int quizId) {
        try {
            return quizRepository.addUserQuiz(userId, quizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkExistQuestion(int quizId, int quesId) {
        try {
            return quizRepository.checkExistQuestion(quizId, quesId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addMark(float mark, int userQuizId) {
        try {
            return quizRepository.addMark(mark, userQuizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMark(String userId, String quizId) {
        try {
            return quizRepository.getMark(userId, quizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "--";
    }
    public boolean deleteQuizSetting(int quizId){
        try {
            return quizRepository.deleteQuizSetting(quizId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
