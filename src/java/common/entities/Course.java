/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

/**
 *
 * @author Admin
 */
public class Course {

    private int id;
    private String imageLink;
    private String courseName;
    private float price;
    private String description;
    private String tag;

    public Course() {
    }

    public Course(int id, String imageLink, String courseName,String description,float price,String tag) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.price = price;
        this.description = description;
        this.tag = tag;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", imageLink=" + imageLink 
                + ", courseName=" + courseName  + ", description=" + description 
                + ", price=" 
           + price +  ", tag=" + tag +'}';
    }
    
    

}
