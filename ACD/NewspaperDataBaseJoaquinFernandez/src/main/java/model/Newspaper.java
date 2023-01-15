package model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "newspaper")
@NamedQueries({@NamedQuery(name = "GET_ALL_NEWSPAPERS",
        query = " from Newspaper ")})
public class Newspaper {
    @Id
    private int id;
    @Column(name = "name_newspaper")
    private String name_newspaper;
    @Column(name = "release_date")
    private LocalDate release_date;

    @OneToMany(mappedBy = "newspaper", cascade = {CascadeType.PERSIST})
    private Collection<Article> articles;

    public Newspaper(String nameNewspaper, String dateNewspaper) {
        this.name_newspaper = nameNewspaper;
        this.release_date= LocalDate.parse(dateNewspaper);
    }

    public Newspaper(int id, String nameNewspaper, String dateNewspaper) {
        this.id = id;
        this.name_newspaper = nameNewspaper;
        this.release_date = LocalDate.parse(dateNewspaper);
    }


}
