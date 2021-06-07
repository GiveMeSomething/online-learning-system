/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

/**
 *
 * @author AS
 */
public class Category {
    private String categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(String catId, String catName) {
        this.categoryId = catId;
        this.categoryName = catName;
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

    @Override
    public String toString() {
        return "Category{" + "catId=" + categoryId + ", catName=" + categoryName + '}';
    }
    
}
