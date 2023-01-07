package model;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name_article;
    @ManyToOne
    @JoinColumn(name = "id_newspaper", nullable = false)
    private Newspaper newspaper;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    private ArticleType type;


}
