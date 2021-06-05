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
public class Post {

    private String postId;
    private String thumbnail;
    private String categoryId;
    private String title;
    private String briefInfo;
    private String description;
    private String feature;
    private String statusId;
    private String authorId;
    private String updatedDate;

    public Post() {
    }

    public Post(String postId, String thumbnail, String categoryId, String title, String briefInfo, String description, String feature, String statusId, String authorId, String updatedDate) {
        this.postId = postId;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
        this.title = title;
        this.briefInfo = briefInfo;
        this.description = description;
        this.feature = feature;
        this.statusId = statusId;
        this.authorId = authorId;
        this.updatedDate = updatedDate;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", thumbnail=" + thumbnail + ", categoryId=" + categoryId + ", title=" + title + ", briefInfo=" + briefInfo + ", description=" + description + ", feature=" + feature + ", statusId=" + statusId + ", authorId=" + authorId + ", updatedDate=" + updatedDate + '}';
    }

    
}
