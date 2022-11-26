package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Reader;

import java.util.List;

public interface DaoReaders {
    Single<Either<String, List<Reader>>> getReaders();

    Single<Either<String, Reader>> saveReader(Reader reader);

    Single<Either<String, Reader>> updateReader(Reader reader);

    Single<Either<String, Boolean>> deleteReader(int id);
}
