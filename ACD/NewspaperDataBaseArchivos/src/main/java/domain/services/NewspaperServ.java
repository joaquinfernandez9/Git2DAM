package domain.services;

import domain.modelo.Newspaper;
import io.vavr.control.Either;

import java.util.List;

public interface NewspaperServ {
    List<Newspaper> getAll();

    Either<String, Boolean> deleteNewspaper(int id);

    boolean newspaperContainsArticles(int idNewspaper);
}
