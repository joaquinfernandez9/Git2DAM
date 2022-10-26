package model;

import lombok.Data;


@Data
public class Subscription {
    private int idReader;
    private int idNewspaper;

    public Subscription(int idReader, int idNewspaper) {
        this.idReader = idReader;
        this.idNewspaper = idNewspaper;
    }
}
