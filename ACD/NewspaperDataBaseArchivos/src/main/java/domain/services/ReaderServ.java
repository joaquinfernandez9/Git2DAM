package domain.services;

import domain.modelo.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface ReaderServ {
    Either<String, List<Reader>> getAll();

    List<Reader> readersSubscribed(int idNewspaper);

    List<Reader> getReadersFromArticleType(String descType);

    boolean appendReadArticle(int idReader, int idArticle, int rating);

    void deleteReader(int idReader);
}
