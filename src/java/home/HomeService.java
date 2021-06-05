/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import common.entities.Category;
import java.util.List;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class HomeService {
    private final HomeRepository homeRepository;

    public HomeService() {
        homeRepository = new HomeRepository();
    }
    
    public List<Category> getAllCategory() {
        try {
            return homeRepository.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
