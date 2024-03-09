package model;

import java.io.Serializable;

public class Toy implements Serializable {
    private String name;
    private int price;
    private int stock;
    private ToyType type;

    public Toy(String name, int price, int stock, ToyType type) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ToyType getType() {
        return type;
    }

    public void setType(ToyType type) {
        this.type = type;
    }
}
