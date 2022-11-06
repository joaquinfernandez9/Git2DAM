package dao;

import io.vavr.control.Either;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;

import java.util.List;

public interface DaoQuerys {
    //Get the description and the number of readers of each article
    Either<Integer, List<QueryDescNumber>> getAll();

    //spring momento
//    Get the articles of a given type, together with the name of the newspaper
    List<QueryArticlesNewspaper> getAll(String description);

    // Get the articles with a rating lower than 5 of a given newspaper, indicating if the reader has
    //rated more than 4 articles with a lower-than-5 rating
    List<QueryArticleRating> getArticles(String newspaperName);
}
