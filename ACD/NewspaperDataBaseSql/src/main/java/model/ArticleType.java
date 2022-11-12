package model;

import lombok.Data;

@Data
public class ArticleType {
    private int id;
    private String description;

    public ArticleType(String linea) {
        String[] charArray = linea.split(";");
        this.id = Integer.parseInt(charArray[0]);
        this.description = charArray[1];
    }

    public String toStringTextFile() {
        return id + ";" +
                description;
    }

    public ArticleType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ArticleType() {
    }

    @Override
    public String toString() {
        return description;
    }
}
