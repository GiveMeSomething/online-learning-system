/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class Slider {

    private int id;
    private String image;
    private String title;
    private String backlink;
    private Status status;
    private String note;

    public Slider() {
    }

    public Slider(int id, String image, String title, String backlink, Status status, String note) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.backlink = backlink;
        this.status = status;
        this.note = note;
    }

    public Slider(String image, String title, Status status, String note) {
        this.image = image;
        this.title = title;
        this.status = status;
        this.note = note;
    }
    
    public Slider(String title, Status status, String note) {
        this.title = title;
        this.status = status;
        this.note = note;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Slider{" + "id=" + id + ", image=" + image + ", title=" + title + ", backlink=" + backlink + ", status=" + status + ", note=" + note + '}';
    }

}
