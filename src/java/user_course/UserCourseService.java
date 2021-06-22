/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.Category;
import common.entities.CourseRegistation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserCourseService {

    private final UserCourseRepository userRepository;

    public UserCourseService() {
        this.userRepository = new UserCourseRepository();
    }

    public boolean updateStatus(int userId, int courseId, int status) {
        try {
            return userRepository.updateStatus(userId, courseId, status);
        } catch (Exception e) {
        }
        return false;
    }

    public List<Category> getAllCategory() {
        try {
            return userRepository.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countCourseUserRegis() {
        try {
            return userRepository.countCourseUserRegis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<CourseRegistation> getCourseUserRegister(int userId, int index) {
        try {
            return userRepository.getCourseUserRegister(userId, index);
        } catch (Exception e) {
        }
        return null;
    }

    public List<CourseRegistation> searchCourseByCategory(int userId, int categoryId) {
        try {
            return userRepository.searchCourseByCategory(userId, categoryId);
        } catch (Exception e) {
        }
        return null;
    }

    public List<CourseRegistation> searchCourseByTitle(int userId, String txtSearch) {
        try {
            return userRepository.searchCourseByTitle(userId, txtSearch);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        UserCourseService dao = new UserCourseService();
        List<Category> list = dao.getAllCategory();
        for (Category o : list) {
            System.out.println(o);
        }
    }
}
