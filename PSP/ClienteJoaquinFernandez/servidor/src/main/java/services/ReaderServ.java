package services;


import io.vavr.control.Either;
import model.Reader;

import java.util.List;

public interface ReaderServ {
    Either<Integer, List<Reader>> getAll(int idNews, String description);

    Either<Integer, Reader> get(int id);

    int deleteReader(int id);

    int update(Reader reader);

    int add(Reader reader);

    int appendReadArticle(Reader reader, int article, int rating);
}
