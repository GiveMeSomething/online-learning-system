/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class DimensionType {
   private int id;
   private String dimension_type_name;

    public DimensionType() {
    }

    public DimensionType(int id, String dimension_type_name) {
        this.id = id;
        this.dimension_type_name = dimension_type_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDimension_type_name() {
        return dimension_type_name;
    }

    public void setDimension_type_name(String dimension_type_name) {
        this.dimension_type_name = dimension_type_name;
    }
    
    

    @Override
    public String toString() {
        return "DimensionType{" + "id=" + id + ", dimension_type_name=" + dimension_type_name + '}';
    }
   
   
}
