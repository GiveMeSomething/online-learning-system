/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import common.entities.Course;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class SearchService {
    private final SearchRepository searchRepository;
    public SearchService() {
        searchRepository = new SearchRepository();
    }
    public List<Course> searchCourse(int cateID,String searchName) {
        try {
            return searchRepository.searchCourse(cateID,searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Course> getCourseFeature(int cateID) {
        try {
            return searchRepository.getCourseFeature(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int countingCourseListSearch(int cateID,String searchName) {
        try {
            return searchRepository.countingCourseListSearch(cateID,searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
   
}
