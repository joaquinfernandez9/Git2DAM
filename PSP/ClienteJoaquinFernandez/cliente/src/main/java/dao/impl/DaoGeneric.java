package dao.impl;

import com.google.gson.Gson;
import dao.Common;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Error;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Log4j2
public class DaoGeneric {

    public static final String APPLICATION_JSON = Common.APPLICATION_JSON;
    private final Gson gson;

    @Inject
    public DaoGeneric(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<String, T>> safeAPICall(Single<T> apiCall) {
        return apiCall.map(t -> Either.right(t).mapLeft(Object::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::getError);
    }

    public Single<Either<String, Boolean>> safeAPICallToDelete(Single<Response<Object>> apiCall) {
        return apiCall.map(objectResponse -> objectResponse.isSuccessful() ?
                        Either.right(true).mapLeft(Object::toString) :
                        Either.left(getError(new HttpException(objectResponse))
                                .getLeft())
                                .map(objects -> true))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::getError);
    }

    private <T> Either<String, T> getError(Throwable throwable) {
        Either<String, T> error = Either.left("Error de comunicacion");
        if (throwable instanceof HttpException httpException) {
            int code =  httpException.code();
            if (Objects.equals(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).contentType(), MediaType.get("application/json"))) {
                Error api = gson.fromJson(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).charStream(), Error.class);
                error = Either.left(code + api.getMessage());
            } else {
                error = Either.left(Objects.requireNonNull(((HttpException) throwable).response()).message());
            }
        }
        return error;
    }
}
