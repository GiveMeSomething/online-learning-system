/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import common.entities.Account;
import common.entities.Category;
import common.entities.Course;
import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.PricePack;
import common.entities.PricePackage;
import java.io.InputStream;
import common.entities.Status;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
            return courseRepository.getCourseByCategoryId(cateID);
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

    public Course checkCourseExist(String searchName, int cateID) {
        try {
            return courseRepository.checkCourseExist(searchName, cateID);
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

    public boolean updateSubjectInformation(String courseName, String description, int owner, int status_id, int category_id, int feature, int id) {
        try {
            return courseRepository.updateSubjectInformation(courseName, description, owner, status_id, category_id, feature, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Dimension> getSubjectDimensionByCourseId(int courseId) {
        try {
            return courseRepository.getSubjectDimensionByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteSubjectDimensionByCourseId(int courseId, int dimensionId) {
        try {
            return courseRepository.deleteSubjectDimensionByCourseId(courseId, dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDimension(int typeId, String name, String description) {
        try {
            return courseRepository.addDimension(typeId, name, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Dimension getDimensionId(String dimension) {
        try {
            return courseRepository.getDimensionId(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addDimensionCourse(int courseId, int dimensionId) {
        try {
            return courseRepository.addDimensionCourse(courseId, dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Dimension getDimensionDetail(int dimensionId) {
        try {
            return courseRepository.getDimensionDetail(dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSubjectDimension(int typeId, String name, String description, int dimensionId) {
        try {
            return courseRepository.updateSubjectDimension(typeId, name, description, dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addNewSubject(Course course, InputStream inputStream) {
        try {
            return courseRepository.addNewSubject(course, inputStream);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDimensionType(String dimension) {
        try {
            return courseRepository.addDimensionType(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public DimensionType getDimensionTypeDetail(String dimension) {
        try {
            return courseRepository.getDimensionTypeDetail(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, String> getOwners() {
        try {
            return courseRepository.getOwners();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getSubjectList(String keyword, int categoryId, Status status, int teacherId) {
        try {
            return courseRepository.getSubjectList(keyword, categoryId, status, teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<DimensionType> getAllDimenstionType() {
        try {
            return courseRepository.getAllDimenstionType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, String> getCourses() {
        try {
            return courseRepository.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    
    public Course getCategoryByCourseId(int courseId){
        try {
            return courseRepository.getCategoryByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Account getRoleByUserEmail(String email){
        try {
            return courseRepository.getRoleByUserEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateSubjectInformation(String courseName, String description, int category_id, int feature, int id){
        try {
            return courseRepository.updateSubjectInformation(courseName, description, category_id, feature, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Course getCourseNameLessonList(int courseId){
        try {
            return courseRepository.getCourseNameLessonList(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
