package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "readarticle")
@NamedQuery(name = "GET_ALL_READ_ARTICLES_READER",
        query = "from ReadArticle where reader.id=:idReader")
@NamedQuery(name = "DELETE_READ_ARTICLES_READER",
        query = "delete from ReadArticle where reader.id=:idReader")
public class ReadArticle {
    @Column
    private int ranking;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_reader", referencedColumnName = "id", nullable = false)
    private Reader reader;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id", nullable = false)
    private Article article;


    public ReadArticle(Reader reader, Article article, int ranking) {
        this.reader = reader;
        this.article = article;
        this.ranking = ranking;
    }
}
