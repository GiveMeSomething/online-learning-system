/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import common.entities.Category;
import common.entities.Course;
import common.entities.Dimension;
import common.entities.DimensionType;
import common.entities.PricePackage;
import common.entities.Status;
import java.sql.SQLException;
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
    
    public Dimension getDimensionDetail(int dimensionId){
        try {
            return courseRepository.getDimensionDetail(dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateSubjectDimension(int typeId,String name,String description,int dimensionId){
        try {
           return courseRepository.updateSubjectDimension(typeId, name, description, dimensionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addDimensionType(String dimension){
        try {
           return courseRepository.addDimensionType(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public DimensionType getDimensionTypeDetail(String dimension){
         try {
            return courseRepository.getDimensionTypeDetail(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<ArrayList<String>> getSubjectList(String keyword, int categoryId, Status status, int teacherId) {
        try {
            if (categoryId <= 0) {
                categoryId = -1;
            }
            return courseRepository.getSubjectList(keyword, categoryId, status, teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
