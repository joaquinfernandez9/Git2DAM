package dao;

import domain.modelo.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReader {
    Either<String, Reader> get(int id);

    Either<String, List<Reader>> getAll();

    Either<String, Boolean> delete(int id);
}
