package dao;

import domain.modelo.Reader;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface DaoReader {
    Either<String, List<Reader>> getAll();

    Either<String, Reader> get(int id);

    int delete(int id);

    boolean update(int id, String name, LocalDate date);

    int add(int id, String name, LocalDate date);

}
