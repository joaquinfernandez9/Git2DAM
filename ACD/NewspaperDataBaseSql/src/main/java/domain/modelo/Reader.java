package domain.modelo;

import dao.localDate.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reader {
    private int id;
    private String name;
    private LocalDate dateOfBirth;

    public Reader(int id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }


}



