/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;
import common.entities.Category;
import common.entities.Course;
import java.util.List;
/**
 *
 * @author Nguyen Khanh Toan
 */
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }
    
    public List<Course> getCourseByCateID(int cateID) {
        try {
            return courseRepository.getCourseByCateID(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Course> pagingCourseList(int cateID,int page) {
        try {
            return courseRepository.pagingCourseList(cateID,page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
     public int countingCourseList(int cateID) {
        try {
            return courseRepository.countingCourseList(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public List<Course> getCourseFeature(int cateID) {
        try {
            return courseRepository.getCourseFeature(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public List<Course> searchCourse(String searchName,int cateID) {
        try {
            return courseRepository.searchCourse(searchName,cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Course> sortCoursePrice(int cateID,String price) {
        try {
            return courseRepository.sortCoursePrice(cateID,price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public List<Course> sortCourseAlpha(int cateID,String alpha) {
        try {
            return courseRepository.sortCourseAlpha(cateID,alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public List<Course> pagingCourseList(int cateID,String searchName,int page,String price,String alpha){
         try {
            return courseRepository.pagingCourseList(cateID, searchName, page, price, alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Course> searchCourse(int cateID,String searchName){
         try {
            return courseRepository.searchCourse(cateID, searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public int countingCourseListSearch(int cateID,String searchName){
        try {
            return courseRepository.countingCourseListSearch(cateID, searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Category> getAllCategory() {
        try {
            return courseRepository.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


   

