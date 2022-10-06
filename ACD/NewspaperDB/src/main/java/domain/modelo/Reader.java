package domain.modelo;

import dao.localDate.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@XmlRootElement(name = "reader")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reader {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    @XmlElement(name = "subscriptions")
    private Subscriptions subscriptions;
    @XmlElement(name = "readArticles")
    private ReadArticles readArticles;

    public Reader(int id, String name, LocalDate dateOfBirth,
                  Subscriptions subscriptions,
                  ReadArticles readArticles) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.subscriptions = subscriptions;
        this.readArticles = readArticles;
    }

    public Reader() {}
}



