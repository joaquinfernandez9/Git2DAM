package model;

import lombok.Data;

@Data
public class ReadArticle {
    private int id;
    private int id_reader;
    private int id_article;
    private int ranking;

    public ReadArticle(int id_reader, int id_article, int ranking) {
        this.id_reader = id_reader;
        this.id_article = id_article;
        this.ranking = ranking;
    }

    public ReadArticle() {

    }
}
