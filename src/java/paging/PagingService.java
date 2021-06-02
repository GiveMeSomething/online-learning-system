/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

import entities.Course;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class PagingService {
    private final PagingRepository pagingRepository;
    public PagingService() {
        this.pagingRepository = new PagingRepository();
    }
    public List<Course> pagingCourseList(int cateID,String searchName,int page,String price) {
        try {
            List<Course> result = pagingRepository.pagingCourseList(cateID,searchName,page,price);

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
    
    public int countingCourseList(int cateID,String title) {
        try {
            int result = pagingRepository.countingCourseList(cateID,title);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public List<Course> courseFeature(int cateID) {
        try {
            List<Course> result = pagingRepository.courseFeature(cateID);
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
