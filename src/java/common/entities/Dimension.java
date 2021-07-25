/**
 * Jun 15, 2021
 *
 * @author Nguyen Khanh Toan
 */

package common.entities;


public class Dimension {
    private int id;
    private String type;
    private String name;
    private String description;

    public Dimension() {
    }

    public Dimension(int id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dimension{" + "id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + '}';
    }
    
}