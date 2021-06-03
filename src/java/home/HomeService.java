/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import common.entities.Course;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HomeService {
   private final HomeRepository homeRepository;

    public HomeService() {
        this.homeRepository = new HomeRepository();
    }

    public List<Course> getITCourse() {
        try {
            List<Course> result = homeRepository.getITCourse();

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
    
    public List<Course> getBusinessCourse() {
        try {
            List<Course> result = homeRepository.getBusinessCourse();

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
    public List<Course> getMarketingCourse() {
        try {
            List<Course> result = homeRepository.getMarketingCourse();

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
    public List<Course> getAICourse() {
        try {
            List<Course> result = homeRepository.getAICourse();

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
    public List<Course> getIACourse() {
        try {
            List<Course> result = homeRepository.getIACourse();

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
    public List<Course> getLanguageCourse() {
        try {
            List<Course> result = homeRepository.getLanguageCourse();

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
    public List<Course> getStudentAreViewingCourse() {
        try {
            List<Course> result = homeRepository.getStudentAreViewingCourse();

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
    
    public List<Course> getSiderCourseDetail() {
        try {
            List<Course> result = homeRepository.getSiderCourseDetail();

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
