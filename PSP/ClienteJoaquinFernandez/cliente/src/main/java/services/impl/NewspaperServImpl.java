package services.impl;

import dao.DaoNewspapers;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import services.NewspaperServ;

import java.util.List;

public class NewspaperServImpl implements NewspaperServ {
    private final DaoNewspapers daoNewspapers;

    @Inject
    public NewspaperServImpl(DaoNewspapers daoNewspapers) {
        this.daoNewspapers = daoNewspapers;
    }

    @Override
    public Single<Either<String, List<Newspaper>>> getNewspapers() {
        return daoNewspapers.getNewspapers();
    }

    @Override
    public Single<Either<String, Newspaper>> saveNewspaper(Newspaper newspaper) {
        return daoNewspapers.saveNewspaper(newspaper);
    }

    @Override
    public Single<Either<String, Newspaper>> updateNewspaper(Newspaper newspaper) {
        return daoNewspapers.updateNewspaper(newspaper);
    }

    @Override
    public Single<Either<String, Boolean>> deleteNewspaper(int id) {
        return daoNewspapers.deleteNewspaper(id);
    }

    @Override
    public Single<Either<String, Boolean>> deleteConfirmed(int id) {
        return daoNewspapers.deleteConfirmed(id);
    }


}
