package model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Article {
    private String name;
    private LocalDate release_date;
    private String type;

    private List<ReadArticle> readArticleList;

    public Article(String name) {
        this.name = name;
    }

    public Article(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
