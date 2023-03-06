package dao;

import com.google.gson.Gson;
import domain.modelo.Informe;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit.InformesApi;

import java.util.List;

public class DaoInforme extends DaoGeneric{
    private final InformesApi api;

    @Inject
    public DaoInforme(InformesApi api, Gson gson) {
        super(gson);
        this.api = api;
    }


    public Single<Either<String, List<Informe>>> getAll(){
        return safeApiCall(api.getAll());
    }

    public Single<Either<String, Informe>> get(String nombre){
        return safeApiCall(api.get(nombre));
    }

    public Single<Either<String, Informe>> add(Informe info){
        return safeApiCall(api.add(info));
    }




}
