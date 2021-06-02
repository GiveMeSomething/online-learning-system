/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courselist;
import entities.Course;
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
            List<Course> result = courseListRepository.getCourseByCateID(cateID);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    public List<Course> pagingCourseList(int cateID,int page) {
        try {
            List<Course> result = courseListRepository.pagingCourseList(cateID,page);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
     public int countingCourseList(int cateID) {
        try {
            int result = courseListRepository.countingCourseList(cateID);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
     
     public List<Course> courseFeature(int cateID) {
        try {
            List<Course> result = courseListRepository.courseFeature(cateID);
            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
     
    public List<Course> searchCourse(String searchName,int cateID) {
        try {
            List<Course> result = courseListRepository.searchCourse(searchName,cateID);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    public List<Course> sortCoursePrice(int cateID,String price) {
        try {
            List<Course> result = courseListRepository.sortCoursePrice(cateID,price);
            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
     
 
}


   

