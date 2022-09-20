package domain.modelo;

import lombok.Data;

@Data
public class Article {
    private int articleID;
    private String title;
    private String description;
    private String author;
//    private int newspaperID;
//    private int typeID;

    public Article(int articleID,
                   String title,
                   String description,
                   String author/*,
                   int newspaperID,
                   int typeID*/) {
        this.articleID = articleID;
        this.title = title;
        this.description = description;
        this.author = author;
//        this.newspaperID = newspaperID;
//        this.typeID = typeID;
    }
}
