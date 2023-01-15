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
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    private ArticleType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article(int id) {
        this.id = id;
    }

    public String getName_article() {
        return name_article;
    }

    public void setName_article(String name_article) {
        this.name_article = name_article;
    }

    public Newspaper getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }


}
