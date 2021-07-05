/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class CourseRegistation {

    private int id;
    private String title;
    private User user;
    private Date registationTime;
    private String packages;
    private double totalCost;
    private int status;
    private Date validFrom;
    private Date validTo;

    public CourseRegistation() {
    }

    public CourseRegistation(int id, String title, User user,
            String packages, double totalCost, int status, Date validFrom, Date validTo) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.packages = packages;
        this.status = status;
        this.totalCost = totalCost;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public CourseRegistation(int id, String title, Date registationTime, String packages, double totalCost, int status, Date validFrom, Date validTo) {
        this.id = id;
        this.title = title;
        this.registationTime = registationTime;
        this.packages = packages;
        this.totalCost = totalCost;
        this.status = status;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRegistationTime() {
        return registationTime;
    }

    public void setRegistationTime(Date registationTime) {
        this.registationTime = registationTime;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return "CourseRegistation{" + "id=" + id + ", title=" + title + ", registationTime=" + registationTime + ", packages=" + packages + ", totalCost=" + totalCost + ", status=" + status + ", validFrom=" + validFrom + ", validTo=" + validTo + '}';
    }

}
