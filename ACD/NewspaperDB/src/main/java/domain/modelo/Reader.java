package domain.modelo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {
    private int readerID;
    private String readerName;
    private LocalDate birthDate;

    public Reader(int readerID,
                  String readerName,
                  LocalDate birthDate) {
        this.readerID = readerID;
        this.readerName = readerName;
        this.birthDate = birthDate;
    }
}
