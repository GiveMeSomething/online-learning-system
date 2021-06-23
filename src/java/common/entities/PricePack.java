/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;

/**
 *
 * @author ADMIN Do PricePackage khai báo thiếu nên phải thêm cái package này
 */
public class PricePack {

    private int id;
    private int duration;
    private String name;
    private double price;
    private Status status;
    private String descriptions;
    private double discount;

    public PricePack() {
    }

    public PricePack(int id, int duration, String name, double price, Status status, String descriptions, double discount) {
        this.id = id;
        this.duration = duration;
        this.name = name;
        this.price = price;
        this.status = status;
        this.descriptions = descriptions;
        this.discount = discount;
    }

    public double getSalePrice() {
        double rate = price - (price * (discount / 100));
        return (double) Math.round(rate * 100) / 100;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "PricePack{" + "id=" + id + ", duration=" + duration + ", name=" + name + ", price=" + price + ", status=" + status + ", descriptions=" + descriptions + ", discount=" + discount + '}';
    }

}
