package domain.modelo;

import lombok.Data;

@Data
public class Newspaper {
    private int newspaperID;
    private String newspaperName;
    private double price;
    private String director;

    public Newspaper(int newspaperID,
                     String newspaperName,
                     double price,
                     String director) {
        this.newspaperID = newspaperID;
        this.newspaperName = newspaperName;
        this.price = price;
        this.director = director;
    }
}
