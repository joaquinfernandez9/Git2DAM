package dao.api;

import domain.Vault;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.*;

import java.util.List;

public interface VaultAPI {
    @GET("folder/")
    Single<List<Vault>> getAll();

    @POST("folder/add")
    Single<Vault> addVault(@Body Vault vault);

    @DELETE("folder/delete")
    Single<Void> deleteVault(@Body Vault vault);


}
