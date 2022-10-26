package services;

import model.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ReaderServ {
    Either<Integer, List<Reader>> getAll(int idNews, int num);

    Either<Integer, Reader> get(int id);

    int deleteReader(int id);

    int update(Reader reader);

    int add(Reader reader);


//    List<Reader> getReadersFromArticleType(String descType);

//    boolean appendReadArticle(int idReader, int idArticle, int rating);
}
