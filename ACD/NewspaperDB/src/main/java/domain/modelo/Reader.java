package domain.modelo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {
    private int readerID;
    private String readerName;
    private LocalDate birthDate;


}
