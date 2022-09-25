package modelo;

import javax.persistence.*;

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_reader")
    private String nameReader;

    @Column(name = "birth_reader")
    private java.sql.Date birthReader;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameReader() {
        return this.nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public java.sql.Date getBirthReader() {
        return this.birthReader;
    }

    public void setBirthReader(java.sql.Date birthReader) {
        this.birthReader = birthReader;
    }

    public java.sql.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }
}
