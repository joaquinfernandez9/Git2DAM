package dao;

import model.Article;
import model.ArticleType;
import model.Newspaper;
import model.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReader {
    Either<Integer, List<Reader>> getAll();

    Either<Integer, List<Reader>> getAll(Newspaper idNews);

    Either<Integer, List<Reader>> getAll(ArticleType description);

    Either<Integer, Reader> get(int id);

    int delete(int id);


}
