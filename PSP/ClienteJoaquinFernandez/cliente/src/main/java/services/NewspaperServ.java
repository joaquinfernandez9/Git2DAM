package services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Newspaper;

import java.util.List;

public interface NewspaperServ {
    Single<Either<String, List<Newspaper>>> getNewspapers();

    Single<Either<String, Newspaper>> saveNewspaper(Newspaper newspaper);

    Single<Either<String, Newspaper>> updateNewspaper(Newspaper newspaper);

    Single<Either<String, Boolean>> deleteNewspaper(int id);

    Single<Either<String, Boolean>> deleteConfirmed(int id);
}
