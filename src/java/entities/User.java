/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class User {
    private int id;
    private String image;
    private String fullName;
    private int gender;
    private String email;
    private String address;
    private int status;
    private String mobile;

    public User() {
    }

    public User(int id, String image, String fullName, int gender,
            String email, String address, int status, String mobile) {
        this.id = id;
        this.image = image;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.status = status;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", image=" + image 
                + ", fullName=" + fullName + ", gender=" + gender 
                + ", email=" + email + ", address=" + address + ", status="
                + status + ", mobile=" + mobile + '}';
    }
    
    
    
    
    
}
