package dao;

import io.vavr.control.Either;
import model.Reader;

import java.util.List;

public interface DaoReader {
    List<Reader> getAll(int idNews, String description);

    Reader get(int id);

    void delete(int id);

    Reader update(Reader r);

    Reader add(Reader r);

}
