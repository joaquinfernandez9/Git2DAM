package domain.modelo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {
    private int readerID;
    private String readerName;
    private LocalDate birthDate;

    public Reader(String linea) {
        String[] charArray = linea.split(";");
        this.readerID = Integer.parseInt(charArray[0]);
        this.readerName = charArray[1];
        this.birthDate = LocalDate.parse(charArray[2]);
    }

    public String toStringTextFile() {
        return readerID + ";" + readerName +
                ";" + birthDate;
    }


}
