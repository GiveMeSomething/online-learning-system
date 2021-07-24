/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import common.entities.Status;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService() {
        lessonRepository = new LessonRepository();
    }

    public boolean addLessonDetail(Lesson lesson) {
        try {
            return lessonRepository.addLessonDetail(lesson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLessonDetail(Lesson lesson, int id) {
        try {
            return lessonRepository.updateLessonDetail(lesson, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Lesson checkLessonExist(Lesson lesson) {
        try {
            return lessonRepository.checkLessonExist(lesson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLesson(int id) {
        try {
            return lessonRepository.getLesson(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Lesson> getLessonList(int subjectId, String keyword) {
        try {
            ArrayList<Lesson> result = this.lessonRepository.getLessonList(subjectId, keyword);

            if (result.size() <= 0) {
                return null;
            } else {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateLessonStatus(int lessonId, Status status) {
        try {
            return this.lessonRepository.updateLessonStatus(lessonId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Lesson> getLessonsByCourseId(int courseId) {
        try {
            return lessonRepository.getLessonsByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLessonDetailByLessonId(int lessonId) {
        try {
            return lessonRepository.getLessonDetailByLessonId(lessonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getMinLessonIdByCourseId(int courseId) {
        try {
            return lessonRepository.getMinLessonIdByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMaxLessonIdByCourseId(int courseId) {
        try {
            return lessonRepository.getMaxLessonIdByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getDoneLessonByCourseId(int courseId) {
        try {
            return lessonRepository.getDoneLessonByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean updateDoneLesson(String lessonId){
        try {
            return lessonRepository.updateDoneLesson(lessonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateUndoneLesson(String lessonId){
        try {
            return this.lessonRepository.updateUndoneLesson(lessonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Lesson> getAllLesson(){
        try {
            return lessonRepository.getAllLesson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
