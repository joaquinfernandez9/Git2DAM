package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    private int id;
    private String name;

    private String cancellationDate;


    public Reader(String text, String value) {
        this.name = text;
        this.cancellationDate = value;
    }


    public Reader(int id, String text, String value, Login login) {
        this.id = id;
        this.name = text;
        this.cancellationDate = value;
    }

    public Reader(int id) {
        this.id = id;
    }
}



