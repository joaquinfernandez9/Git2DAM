package domain.modelo;

import lombok.Data;

@Data
public class Article {
    private int articleID;
    private String title;
    private String author;
    private int newspaperID;
    private int typeID;

    public Article(String linea) {
        String[] charArray = linea.split(";");
        this.articleID = Integer.parseInt(charArray[0]);
        this.title = charArray[1];
        this.author = charArray[2];
        this.newspaperID = Integer.parseInt(charArray[3]);
        this.typeID = Integer.parseInt(charArray[4]);
    }

    public String toStringTextFile() {
        return articleID + ";" + title + ";" + author
                + ";" + newspaperID + ";" + typeID;
    }

}
