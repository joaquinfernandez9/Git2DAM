package modelo.mongo;

import lombok.Data;

@Data
public class Item {
    private String name;
    private Double price;
    private int amount;

    public Item(String name, Double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}