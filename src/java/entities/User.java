/**
 * 22/05/2021
 *
 * @author Admin
 */
package entities;

public class User {

    private int id;
    private String image;
    private String fullName;
    private String gender;
    private String email;
    private String address;
    private String status;
    private String phoneNumber;

    public User(int id, String avatarURL, String fullName, String gender, String email, String address, String status, String phoneNumber) {
        this.id = id;
        this.image = avatarURL;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", image=" + image + ", fullName=" + fullName + ", gender=" + gender + ", email=" + email + ", address=" + address + ", status=" + status + ", phoneNumber=" + phoneNumber + '}';
    }

}
