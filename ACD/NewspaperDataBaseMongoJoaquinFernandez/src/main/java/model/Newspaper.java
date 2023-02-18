package model;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Newspaper {
    private ObjectId _id;
    private String name;

    private String relDate;

    private List<Article> articles;

    private List<Reader> readers;


    public Newspaper(String name, String relDate) {
        this.name = name;
        this.relDate = relDate;
    }

    public Newspaper(ObjectId _id, String name, String relDate) {
        this._id = _id;
        this.name = name;
        this.relDate = relDate;
    }

    public Newspaper(ObjectId _id, String name, String relDate, List<Article> articles, List<Reader> readers) {
        this._id = _id;
        this.name = name;
        this.relDate = relDate;
        this.articles = articles;
        this.readers = readers;
    }

    public Newspaper(int i) {
        this._id = new ObjectId();
    }
}
