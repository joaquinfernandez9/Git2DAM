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
public class ReadArticle {
    @Id
    private int id;
    @Column
    private int ranking;
    @ManyToOne
    @JoinColumn(name = "id_reader", referencedColumnName = "id", nullable = false)
    private Reader reader;
    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id", nullable = false)
    private Article article;


    public ReadArticle(Reader reader, Article article, int ranking) {
        this.reader = reader;
        this.article = article;
        this.ranking = ranking;
    }
}
