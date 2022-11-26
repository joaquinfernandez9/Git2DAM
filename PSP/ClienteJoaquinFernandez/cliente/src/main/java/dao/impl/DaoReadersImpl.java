package dao.impl;

import com.google.gson.Gson;
import dao.DaoReaders;
import dao.retrofit.llamadas.ReadersApi;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Reader;

import java.util.List;

public class DaoReadersImpl extends DaoGeneric implements DaoReaders {

    private final ReadersApi readerApi;

    @Inject
    public DaoReadersImpl(Gson gson, ReadersApi readerApi) {
        super(gson);
        this.readerApi = readerApi;
    }

    @Override
    public Single<Either<String, List<Reader>>> getReaders() {
        return safeAPICall(readerApi.getReaders());
    }

    @Override
    public Single<Either<String, Reader>> saveReader(Reader reader) {
        return safeAPICall(readerApi.addReader(reader));
    }

    @Override
    public Single<Either<String, Reader>> updateReader(Reader reader) {
        return safeAPICall(readerApi.updateReader(reader));
    }

    @Override
    public Single<Either<String, Boolean>> deleteReader(int id) {
        return safeAPICallToDelete(readerApi.deleteReader(id));
    }

}
