package modelo;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_article")
    private String nameArticle;

    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "id_newspaper")
    private Integer idNewspaper;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameArticle() {
        return this.nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public Integer getIdType() {
        return this.idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdNewspaper() {
        return this.idNewspaper;
    }

    public void setIdNewspaper(Integer idNewspaper) {
        this.idNewspaper = idNewspaper;
    }
}
