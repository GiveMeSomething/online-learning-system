/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import java.util.List;
import common.entities.Category;
import common.entities.Course;
import common.entities.PricePackage;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nguyen Khanh Toan
 */
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    public Course getCourse(int id) {
        try {
            Course result = courseRepository.getCourse(id);

            if (result == null) {
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

    public ArrayList<PricePackage> getCoursePackage(int id) {
        try {
            return courseRepository.getCoursePackage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Course> getCourses(String category) {
        try {
            List<Course> result = courseRepository.getCourses(category);

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

    public List<Course> getFeaturedCourse() {
        try {
            List<Course> result = courseRepository.getFeaturedCourse();

            if (result == null) {
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

    public List<Course> getSiderCourseDetail() {
        try {
            List<Course> result = courseRepository.getSiderCourseDetail();

            if (result == null) {
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

    public List<Course> getCourseByCateID(int cateID) {
        try {
            return courseRepository.getCourseByCateID(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Course> pagingCourseList(int cateID, int page) {
        try {
            return courseRepository.pagingCourseList(cateID, page);
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

    public List<Course> searchCourse(String searchName, int cateID) {
        try {
            return courseRepository.searchCourse(searchName, cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Course> pagingCourseList(int cateID, String searchName, int page, String price, String alpha) {
        try {
            return courseRepository.pagingCourseList(cateID, searchName, page, price, alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> searchCourse(int cateID, String searchName) {
        try {
            return courseRepository.searchCourse(cateID, searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countingCourseListSearch(int cateID, String searchName) {
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
