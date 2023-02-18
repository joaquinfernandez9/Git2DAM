package data.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "newspaper")
public class NewspaperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private String releaseDate;

    @OneToMany(mappedBy = "newspaper", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;

    public NewspaperEntity(int id, String name, String releaseDate) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public NewspaperEntity() {

    }
}
