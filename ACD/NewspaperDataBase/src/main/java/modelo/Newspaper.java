package modelo;

import javax.persistence.*;

@Entity
@Table(name = "newspaper")
public class Newspaper {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_newspaper")
    private String nameNewspaper;

    @Column(name = "release_date")
    private java.sql.Date releaseDate;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameNewspaper() {
        return this.nameNewspaper;
    }

    public void setNameNewspaper(String nameNewspaper) {
        this.nameNewspaper = nameNewspaper;
    }

    public java.sql.Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
