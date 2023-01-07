package dao;

import model.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReader {
    Either<Integer, List<Reader>> getAll(int idNews, String description);

    Either<Integer, Reader> get(int id);



}
