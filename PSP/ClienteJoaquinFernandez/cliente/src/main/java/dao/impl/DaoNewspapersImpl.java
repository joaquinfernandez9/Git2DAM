package dao.impl;

import com.google.gson.Gson;
import dao.DaoNewspapers;
import dao.retrofit.llamadas.NewspaperApi;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Newspaper;

import java.util.List;

@Log4j2
public class DaoNewspapersImpl extends DaoGeneric implements DaoNewspapers {

    private final NewspaperApi newspaperApi;

    @Inject
    public DaoNewspapersImpl(NewspaperApi newspaperApi, Gson gson) {
        super(gson);
        this.newspaperApi = newspaperApi;
    }

    @Override
    public Single<Either<String, List<Newspaper>>> getNewspapers() {
        return safeAPICall(newspaperApi.getNewspapers());
    }

    @Override
    public Single<Either<String, Newspaper>> saveNewspaper(Newspaper newspaper) {
        return safeAPICall(newspaperApi.saveNewspaper(newspaper));
    }

    @Override
    public Single<Either<String, Newspaper>> updateNewspaper(Newspaper newspaper) {
        return safeAPICall(newspaperApi.updateNewspaper(newspaper));
    }

    @Override
    public Single<Either<String, Boolean>> deleteNewspaper(int id) {
        return safeAPICallToDelete(newspaperApi.deleteNewspaper(id));
    }

    @Override
    public Single<Either<String, Boolean>> deleteConfirmed(int id) {
        return safeAPICallToDeleteInt(newspaperApi.deleteConfirmed(id));
    }
}
