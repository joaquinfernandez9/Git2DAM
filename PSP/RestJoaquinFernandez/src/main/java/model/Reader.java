package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {
    private int id;
    private String name_reader;
    private LocalDate birth_reader;
    private Login login;

    public Reader(int id, String name_reader, LocalDate birth_reader, Login login) {
        this.id = id;
        this.name_reader = name_reader;
        this.birth_reader = birth_reader;
        this.login = login;
    }

    public Reader(int id, String name_reader, LocalDate birth_reader) {
        this.id = id;
        this.name_reader = name_reader;
        this.birth_reader = birth_reader;
    }

    public Reader(String name_reader, LocalDate birth_reader) {
        this.name_reader = name_reader;
        this.birth_reader = birth_reader;
    }

    public Reader() {
    }
}



