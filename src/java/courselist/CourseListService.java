/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courselist;
import common.entities.Course;
import java.util.List;
/**
 *
 * @author Nguyen Khanh Toan
 */
public class CourseListService {
    private final CourseListRepository courseListRepository;

    public CourseListService() {
        this.courseListRepository = new CourseListRepository();
    }
    
    public List<Course> getCourseByCateID(int cateID) {
        try {
            return courseListRepository.getCourseByCateID(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Course> pagingCourseList(int cateID,int page) {
        try {
            return courseListRepository.pagingCourseList(cateID,page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
     public int countingCourseList(int cateID) {
        try {
            return courseListRepository.countingCourseList(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public List<Course> getCourseFeature(int cateID) {
        try {
            return courseListRepository.getCourseFeature(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public List<Course> searchCourse(String searchName,int cateID) {
        try {
            return courseListRepository.searchCourse(searchName,cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Course> sortCoursePrice(int cateID,String price) {
        try {
            return courseListRepository.sortCoursePrice(cateID,price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public List<Course> sortCourseAlpha(int cateID,String alpha) {
        try {
            return courseListRepository.sortCourseAlpha(cateID,alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
}


   

