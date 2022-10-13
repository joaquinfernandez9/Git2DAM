package domain.modelo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Newspaper {
    private int newspaperID;
    private String newspaperName;
    private double price;
    private String director;

    public Newspaper(String linea) {
        String[] charArray = linea.split(";");
        this.newspaperID = Integer.parseInt(charArray[0]);
        this.newspaperName = charArray[1];
        this.price = Double.parseDouble(charArray[2]);
        this.director = charArray[3];
    }

    public String toStringTextFile() {
        return newspaperID + ";" + newspaperName + ";" + price + ";" + director;
    }


}
