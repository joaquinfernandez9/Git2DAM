package model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
public class Newspaper {
    private int id;
    private String name_newspaper;
    private LocalDate release_date;

    public Newspaper(int id, String name_newspaper, LocalDate release_date) {
        this.id = id;
        this.name_newspaper = name_newspaper;
        this.release_date = release_date;
    }

    public Newspaper() {
    }
}
