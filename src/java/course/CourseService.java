/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import java.util.List;
import common.entities.Category;
import common.entities.Course;
import common.entities.PricePack;
import common.entities.PricePackage;
import common.entities.Status;
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

//subject
    public Course getSubject(int id) {
        try {
            Course result = courseRepository.getSubject(id);

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

    public boolean updateSubjectInformation(String courseName, String description,int owner, int status_id, int category_id, int feature, int id) {
        try {
            return courseRepository.updateSubjectInformation(courseName, description,owner, status_id, category_id, feature, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
public List<PricePack> getPricePackage(int index) {
        try {
            return courseRepository.pagingPricePackage(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countTotalPricePackage() {
        try {
            return courseRepository.countTotalPricePackage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addPackage(int duration, String name, double price, int status, String descriptions, double discount) {
        try {
            return courseRepository.addPackage(duration, name, price, status, descriptions, discount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePackage(int id) {
        try {
            return courseRepository.deletePackage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editPackage(int id, int duration, String name, double price, int status, String descriptions, double discount) {
        try {
            return courseRepository.editPackage(id, duration, name, price, status, descriptions, discount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
