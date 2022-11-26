package services.impl;

import dao.DaoReaders;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Reader;
import services.ReaderServ;

import java.util.List;

public class ReaderServImpl implements ReaderServ {
    
    private final DaoReaders daoReaders;
    
    @Inject
    public ReaderServImpl(DaoReaders daoReaders) {
        this.daoReaders = daoReaders;
    }
    
    @Override public Single<Either<String, List<Reader>>> getReaders() {
        return daoReaders.getReaders();
    }
    
    @Override public Single<Either<String, Reader>> saveReader(Reader reader) {
        return daoReaders.saveReader(reader);
    }
    
    @Override public Single<Either<String, Reader>> updateReader(Reader reader) {
        return daoReaders.updateReader(reader);
    }
    
    @Override public Single<Either<String, Boolean>> deleteReader(int id) {
        return daoReaders.deleteReader(id);
    }
    
}
