package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Data
public class ReadArticle {
    private int id_reader;
    private int ranking;


    public ReadArticle(Reader r, Article art, int rating) {
        this.id_reader = r.getId();
        this.ranking = rating;
    }
}
