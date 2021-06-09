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
public class Blog {
    private String blogId;
    private String title;
    private String authorId;
    private String updatedDate;
    private String categoryId;
    private String postId;
    private String authorName;
    public Blog() {
    }

    public Blog(String blogId, String title, String authorId, String updatedDate, String categoryId, String postId) {
        this.blogId = blogId;
        this.title = title;
        this.authorId = authorId;
        this.updatedDate = updatedDate;
        this.categoryId = categoryId;
        this.postId = postId;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", title=" + title + ", authorId=" + authorId + ", updatedDate=" + updatedDate + ", categoryId=" + categoryId + ", postId=" + postId + '}';
    }
    public String toString(String authorName) {
        return "Blog{" + "blogId=" + blogId + ", title=" + title + ", authorId=" + authorId + ", updatedDate=" + updatedDate + ", categoryId=" + authorName + ", postId=" + postId + '}';
    }
}
