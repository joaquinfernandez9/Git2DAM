package domain.services;

import io.vavr.control.Either;
import model.querys.QueryArticleRating;
import model.querys.QueryDescNumber;
import model.Reader;
import model.querys.QueryArticlesNewspaper;

import java.util.List;

public interface QuerysServ {
    List<QueryDescNumber> getAll();

    List<Reader> getOldest(int idNewspaper);

    List<QueryArticlesNewspaper> getAll(String description);

    List<QueryArticleRating> getArticles(int idNewspaper);
}
