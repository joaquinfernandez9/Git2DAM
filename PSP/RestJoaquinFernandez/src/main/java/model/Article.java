package model;

import lombok.Data;

@Data
public class Article {
    private int id;
    private String name_article;
    private int id_newspaper;
    private int id_type;

    public Article() {
    }

    public Article(int id, String name_article, int id_newspaper, int id_type) {
        this.id = id;
        this.name_article = name_article;
        this.id_newspaper = id_newspaper;
        this.id_type = id_type;
    }

    public Article(String name_article, int id_newspaper, int id_type) {
        this.name_article = name_article;
        this.id_newspaper = id_newspaper;
        this.id_type = id_type;
    }
}
