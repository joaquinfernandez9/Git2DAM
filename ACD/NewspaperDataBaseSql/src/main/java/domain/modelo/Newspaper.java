package domain.modelo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Newspaper {
    private int newspaperID;
    private String newspaperName;
    private String director;

    public Newspaper(String linea) {
        String[] charArray = linea.split(";");
        this.newspaperID = Integer.parseInt(charArray[0]);
        this.newspaperName = charArray[1];
        this.director = charArray[2];
    }

    public String toStringTextFile() {
        return newspaperID + ";" + newspaperName + ";" + director;
    }


}
