package dao.api;

import domain.Message;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface MessageAPI {
    @GET("/message/{id}")
    Single<Message> getMensaje(@Path("id") String id);

    @GET("/message/all/{id}")
    Single<List<Message>> getAll(@Path("id") String id);

    @POST("/message/add")
    Single<Message> addMensaje(@Body Message message);

    @PUT("/message/update")
    Single<Message> updateMensaje(@Body Message message);

    @DELETE("/message/delete/{id}")
    Single<Response<Void>> deleteMensaje(@Path("id") String id);
}
