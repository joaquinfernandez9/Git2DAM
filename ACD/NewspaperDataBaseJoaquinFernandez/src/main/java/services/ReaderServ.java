package services;

import model.*;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ReaderServ {
    Either<Integer, List<Reader>> getAll(Newspaper idNews, ArticleType description);


    Either<Integer, Reader> get(int id);

    int deleteReader(int id);

    int update(Reader reader);

    int add(Login login);


//    List<Reader> getReadersFromArticleType(String descType);

}
