package model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Reader {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Login login;

    public Reader(int id, String name, LocalDate dateOfBirth, Login login) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
    }

    public Reader(int id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Reader(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Reader() {
    }
}



