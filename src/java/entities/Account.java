/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author AS
 */
public class Account {

    private String userEmail;
    private String password;
    private String roleId;

    public Account() {
    }

    public Account(String userEmail, String password, String roleId) {
        this.userEmail = userEmail;
        this.password = password;
        this.roleId = roleId;
    }

    public Account(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "userEmail=" + userEmail + ", password=" + password + ", roleId=" + roleId + '}';
    }
}
