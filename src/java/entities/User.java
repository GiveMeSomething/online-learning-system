/**
 * 22/05/2021
 *
 * @author Admin
 */
package entities;

public class User {

    private int id;
    private String avatarURL;
    private String fullName;
    private String gender;
    private String email;
    private String role;
    private String address;
    private String status;
    private String phoneNumber;

    public User(int id, String avatarURL, String fullName, String gender, String email,
            String role, String address, String status, String phoneNumber) {
        this.id = id;
        this.avatarURL = avatarURL;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.role = role;
        this.address = address;
        this.status = status;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
