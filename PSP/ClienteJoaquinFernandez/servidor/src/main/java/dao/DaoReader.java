package dao;

import io.vavr.control.Either;
import model.Reader;

import java.util.List;

public interface DaoReader {
    List<Reader> getAll(int idNews, String description);

    Reader get(int id);

    int delete(int id);

    int update(Reader r);

    int add(Reader r);

}
