package domain.modelo;

import lombok.Data;

@Data
public class ArticleType {
    private int typeID;
    private String description;

    public ArticleType(String linea) {
        String[] charArray = linea.split(";");
        this.typeID = Integer.parseInt(charArray[0]);
        this.description = charArray[1];
    }

    public String toStringTextFile() {
        return typeID + ";" +
                description;
    }

}
