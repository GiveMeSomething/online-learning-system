/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

/**
 *
 * @author Asus
 */
public class User {

    private int id;
    private String image;
    private String name;
    private Gender gender;
    private String email;
    private String address;
    private Status status;
    private String mobile;

    public User(String name, Gender gender, String email, Status status, String mobile) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.mobile = mobile;
    }

    public User(int id, String image, String name, Gender gender, String email, String address, Status status, String mobile) {
        this.id = id;
        this.image = image;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
