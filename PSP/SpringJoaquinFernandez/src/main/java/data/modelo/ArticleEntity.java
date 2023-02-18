package data.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "article")
public class ArticleEntity {
    @Id
    private int id;
    @Column(name = "name_article")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id")
    private NewspaperEntity newspaper;

    public ArticleEntity(int id, String name, NewspaperEntity newspaper) {
        this.id = id;
        this.name = name;
        this.newspaper = newspaper;
    }

    public ArticleEntity() {

    }
}
