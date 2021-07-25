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
    private int ownerId;
    private CourseStatus status;
    private Status statusSubject;
    private String category;
    private boolean feature;
    private String tag;
    private String base64Image;

    public Course() {
    }

    public Course(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public Course(int id, String imageLink, String courseName, float price, String description, int ownerId, CourseStatus status, String category, boolean feature, String tag) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.price = price;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
        this.feature = feature;
        this.tag = tag;
    }

    public Course(String courseName, String description, int ownerId, CourseStatus status, String category, Boolean feature) {
        this.courseName = courseName;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
        this.feature = feature;
    }

    public Course(int id, String imageLink, String courseName, float price, String category, String description, String tag) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.tag = tag;
    }

    public Course(int id, String imageLink, String courseName, String description) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.description = description;
    }

    public Course(int id, String imageLink, String courseName, String description, float price, String tag) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.price = price;
        this.description = description;
        this.tag = tag;
    }

    public Course(int id, String imageLink, String courseName, String description, int ownerId, CourseStatus status, String category, boolean feature) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
        this.feature = feature;
    }
    
    public Course(int id, String imageLink, String courseName, String description, int ownerId, CourseStatus status, String category, boolean feature, String base64Image) {
        this.id = id;
        this.imageLink = imageLink;
        this.courseName = courseName;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
        this.feature = feature;
        this.base64Image = base64Image;
    }

    public Course(int id, String courseName, String description, int ownerId, CourseStatus status, String category, boolean feature) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
        this.category = category;
        this.feature = feature;
    }

    public Status getStatusSubject() {
        return statusSubject;
    }

    public void setStatusSubject(Status statusSubject) {
        this.statusSubject = statusSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", imageLink=" + imageLink + ", courseName=" + courseName + ", price=" + price + ", description=" + description + ", ownerId=" + ownerId + ", status=" + status + ", statusSubject=" + statusSubject + ", category=" + category + ", feature=" + feature + ", tag=" + tag + ", base64Image=" + base64Image + '}';
    }
}
