/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.Course1;
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

    public List<Course1> getCourse1() {
        try {
            List<Course1> result = homeRepository.getAllCourse();

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
