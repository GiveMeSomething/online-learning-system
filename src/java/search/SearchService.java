/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import entities.Course;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class SearchService {
    private final SearchRepository searchRepository;
    public SearchService() {
        this.searchRepository = new SearchRepository();
    }
    public List<Course> searchCourse(int cateID,String searchName) {
        try {
            List<Course> result = searchRepository.searchCourse(cateID,searchName);

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
    
    public List<Course> courseFeature(int cateID) {
        try {
            List<Course> result = searchRepository.courseFeature(cateID);
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
    
    public int countingCourseListSearch(int cateID,String searchName) {
        try {
            int result = searchRepository.countingCourseListSearch(cateID,searchName);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
   
}
