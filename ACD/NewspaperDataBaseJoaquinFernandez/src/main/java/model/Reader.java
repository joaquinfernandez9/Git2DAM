package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reader")
@NamedQuery(name = "GET_ALL_READERS", query = "from Reader")
@NamedQuery(name = "GET_ALL_READERS_BY_NEWSPAPER",
        query = "select sub.reader from Subscription sub where sub.id_newspaper = :idNewspaper and sub.cancellation_date is null")
@NamedQuery(name = "DELETE_READ_ARTICLE", query="delete from ReadArticle  ra where ra.reader.id = :idReader")
@NamedQuery(name = "GET_READER_BY_TYPE_DESC", query = "select distinct ra.reader from ReadArticle ra where ra.article.type.description = :description")
@NamedQuery(name= "DELETE_READER_SUSCRIPTIONS", query = "delete from Subscription r where r.id_reader = :idReader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_reader")
    private String name_reader;
    @Column(name = "birth_reader")
    private LocalDate birth_reader;
    @OneToOne(mappedBy = "reader", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST})
    private Login login;

    public Reader(String name_reader, LocalDate birth_reader) {
        this.name_reader = name_reader;
        this.birth_reader = birth_reader;
    }
}



