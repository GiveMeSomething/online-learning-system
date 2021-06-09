/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

/**
 *
 * @author Nguyen Khanh Toan
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private int id;

    public Category(String catId, String catName) {
        this.categoryId = catId;
        this.categoryName = catName;
    }
    
    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", id=" + id + '}';
    }
    
}
