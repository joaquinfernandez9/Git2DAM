package dao;

import com.google.gson.Gson;
import domain.modelo.Raton;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit.RatonesApi;

import java.util.List;

public class DaoRaton extends DaoGeneric
{

    private final RatonesApi api;


    @Inject
    public DaoRaton(Gson gson, RatonesApi api) {
        super(gson);
        this.api = api;
    }

    public Single<Either<String, List<Raton>>> getAll(){
        return safeApiCall(api.getAll());
    }

    public Single<Either<String, Raton>> add(Raton raton){
        return safeApiCall(api.add(raton));
    }



}
