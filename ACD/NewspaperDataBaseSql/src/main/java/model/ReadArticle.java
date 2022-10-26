package model;

import lombok.Data;

@Data
public class ReadArticle {
    private int idReader;
    private int idArticle;
    private int rating;

    public ReadArticle(int idReader, int idArticle, int rating) {
        this.idReader = idReader;
        this.idArticle = idArticle;
        this.rating = rating;
    }

    public ReadArticle() {

    }
}
