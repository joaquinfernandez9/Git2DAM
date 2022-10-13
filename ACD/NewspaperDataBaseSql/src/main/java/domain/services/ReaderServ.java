package domain.services;

import domain.modelo.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface ReaderServ {
    Either<String, List<Reader>> getAll();

    Either<String, Reader> get(int id);

    int deleteReader(int id);

    boolean update(int id, String name, LocalDate date);

    int add(int id, String name, LocalDate date);

//    List<Reader> readersSubscribed(int idNewspaper);

//    List<Reader> getReadersFromArticleType(String descType);

//    boolean appendReadArticle(int idReader, int idArticle, int rating);
}
