package services;

import model.Article;
import model.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ReaderServ {
    Either<Integer, List<Reader>> getAll(int idNews, int num, String description);

    Either<Integer, Reader> get(int id);

    int deleteReader(int id);

    int update(Reader reader);

    int add(Reader reader);


//    List<Reader> getReadersFromArticleType(String descType);

    int appendReadArticle(Reader reader, int article, int rating);
}
