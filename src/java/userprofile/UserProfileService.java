/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userprofile;

import entities.User;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    public UserProfileService() {
        this.userProfileRepository = new UserProfileRepository();
    }
    
    public User getUserProfile(int id) {
        try {
            User result = userProfileRepository.getUserProfile(id);

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

    public void updateUserProfile(String image,String fullName,int gender,String address,
            int status,String mobile,int id) {
        try {
            userProfileRepository.updateUserProfile(image, fullName, gender, address,
                    status, mobile, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
}
