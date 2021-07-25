/**
 * Jun 7, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

public class PricePackage {

    private int id;
    private String name;
    // Anhvd: moi sua thanh double
    private double price;

    public PricePackage(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
