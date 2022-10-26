package model;

import lombok.Data;

@Data
public class Article {
    private int articleID;
    private String title;
    private int newspaperID;
    private int typeID;

    public Article(String linea) {
        String[] charArray = linea.split(";");
        this.articleID = Integer.parseInt(charArray[0]);
        this.title = charArray[1];
        this.newspaperID = Integer.parseInt(charArray[2]);
        this.typeID = Integer.parseInt(charArray[3]);
    }

    public String toStringTextFile() {
        return articleID + ";" + title
                + ";" + newspaperID + ";" + typeID;
    }

}
