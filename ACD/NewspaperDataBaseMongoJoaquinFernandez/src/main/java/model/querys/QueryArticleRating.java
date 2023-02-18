package model.querys;

import lombok.Data;

@Data
public class QueryArticleRating {
    private String name_article;
    private int id;
    private int bad_ratings;
}
