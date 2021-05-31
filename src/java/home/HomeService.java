/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.Category;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class HomeService {
    private final HomeRepository homeRepository;

    public HomeService() {
        this.homeRepository = new HomeRepository();
    }
    
    public List<Category> getAllCategory() {
        try {
            List<Category> result = homeRepository.getAllCategory();
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
