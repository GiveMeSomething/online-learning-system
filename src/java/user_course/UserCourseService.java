/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.Category;
import common.entities.CourseRegistation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;

    public UserCourseService() {
        this.userCourseRepository = new UserCourseRepository();
    }

    public boolean updateStatus(int userId, int courseId, int status) {
        try {
            return userCourseRepository.updateStatus(userId, courseId, status);
        } catch (Exception e) {
        }
        return false;
    }

    public List<Category> getAllCategory() {
        try {
            return userCourseRepository.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countCourseUserRegis() {
        try {
            return userCourseRepository.countCourseUserRegis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<CourseRegistation> getCourseUserRegister(int userId, int index) {
        try {
            return userCourseRepository.getCourseUserRegister(userId, index);
        } catch (Exception e) {
        }
        return null;
    }

    public List<CourseRegistation> searchCourseByCategory(int userId, int categoryId) {
        try {
            return userCourseRepository.searchCourseByCategory(userId, categoryId);
        } catch (Exception e) {
        }
        return null;
    }

    public List<CourseRegistation> searchCourseByTitle(int userId, String txtSearch) {
        try {
            return userCourseRepository.searchCourseByTitle(userId, txtSearch);
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getCourseRegistations(String keyword, Date from, Date to, String orderBy, int userId) {
        try {
            return userCourseRepository.getCourseRegistations(keyword, from, to, orderBy, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
