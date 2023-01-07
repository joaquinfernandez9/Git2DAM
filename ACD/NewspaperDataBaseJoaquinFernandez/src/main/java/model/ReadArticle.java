package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int id_reader;
    @Column
    private int id_article;
    @Column
    private int ranking;


}
