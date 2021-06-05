/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

import common.entities.Course;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class PagingService {
    private final PagingRepository pagingRepository;
    public PagingService() {
        pagingRepository = new PagingRepository();
    }
    public List<Course> pagingCourseList(int cateID,String searchName,int page,String price,String alpha) {
        try {
            return pagingRepository.pagingCourseList(cateID,searchName,page,price,alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int countingCourseList(int cateID,String title) {
        try {
            return pagingRepository.countingCourseList(cateID,title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Course> courseFeature(int cateID) {
        try {
            return pagingRepository.courseFeature(cateID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
