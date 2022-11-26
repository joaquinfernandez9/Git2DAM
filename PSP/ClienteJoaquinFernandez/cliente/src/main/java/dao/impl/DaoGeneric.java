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

    public Single<Either<String, Boolean>> safeAPICallToDeleteInt(Single<Response<Object>> apiCall) {
        return apiCall.map(objectResponse -> objectResponse.isSuccessful() ?
                        Either.right(true).mapLeft(Object::toString) :
                        Either.left(getError(new HttpException(objectResponse))
                                        .getLeft())
                                .map(objects -> true))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(this::getErrorInt);
    }

    private <T> Either<String, T> getErrorInt(Throwable throwable) {
        Either<String, T> error = Either.left(Common.COMMUNICATION_ERROR);
        if (throwable instanceof HttpException httpException) {
            try (ResponseBody responseBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                if (Objects.equals(Objects.requireNonNull(responseBody).contentType(),
                        MediaType.get(APPLICATION_JSON))) {
                    List<Integer> list = Collections.emptyList();
                    List<Integer> apierror = gson.fromJson(responseBody.string(), list.getClass());
                    error = Either.left(apierror.toString());
                } else {
                    error = Either.left(Objects.requireNonNull(httpException.response()).message());
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return error;
    }


    private <T> Either<String, T> getError(Throwable throwable) {
        Either<String, T> error = Either.left(Common.COMMUNICATION_ERROR);
        if (throwable instanceof HttpException httpException) {
            try (ResponseBody responseBody = Objects.requireNonNull(httpException.response()).errorBody()) {
                if (Objects.equals(Objects.requireNonNull(responseBody).contentType(),
                        MediaType.get(APPLICATION_JSON))) {
                    Error apierror = gson.fromJson(responseBody.string(), Error.class);
                    error = Either.left(apierror.getMessage());
                } else {
                    error = Either.left(Objects.requireNonNull(httpException.response()).message());
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return error;
    }
}
