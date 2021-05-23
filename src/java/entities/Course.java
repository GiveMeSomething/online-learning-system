/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Admin
 */
public class Course {
    private int id;
    private String courseName;
    private String subject;
    private String author;
    private String imageLink;
    private float price;
    private String description;

    public Course() {
    }

    public Course(int id, String courseName, String subject, String author, String imageLink, float price, String description) {
        this.id = id;
        this.courseName = courseName;
        this.subject = subject;
        this.author = author;
        this.imageLink = imageLink;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", courseName=" + courseName + ", subject=" + subject + ", author=" + author + ", imageLink=" + imageLink + ", price=" + price + ", description=" + description + '}';
    }
    
}
