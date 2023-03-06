package dao;

import com.google.gson.Gson;
import domain.modelo.error.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import java.io.IOException;
import java.util.Objects;

public class DaoGeneric {
    private final Gson gson;

    @Inject
    public DaoGeneric(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<String, T>> safeApiCall(Single<T> apiCall){
        return apiCall.map(t -> Either.right(t).mapLeft(Object::toString))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::error);
    }

    private <T> Either<String, T> error(Throwable throwable){
        Either<String, T> error = Either.left("Error de Comunicacion");
        if (throwable instanceof HttpException httpException) {
            try (ResponseBody responseBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                if (Objects.equals(Objects.requireNonNull(responseBody).contentType(),
                        MediaType.get("application/json"))) {
                    ApiError apierror = gson.fromJson(responseBody.string(), ApiError.class);
                    error = Either.left(apierror.getMessage());
                } else {
                    error = Either.left(Objects.requireNonNull(httpException.response()).message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return error;
    }

}
