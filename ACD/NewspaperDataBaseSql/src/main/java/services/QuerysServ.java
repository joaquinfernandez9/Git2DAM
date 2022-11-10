package services;

import io.vavr.control.Either;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;

import java.util.List;

public interface QuerysServ {
    Either<Integer, List<QueryDescNumber>> getAll();

    Either<Integer, List<Reader>> getReadersSubscribed(int idNewspaper);

    Either<Integer, List<Reader>> getOldest(int idNewspaper);

    List<QueryArticlesNewspaper> getAll(String description);

    List<QueryArticleRating> getArticles(String newspaperName);
}
