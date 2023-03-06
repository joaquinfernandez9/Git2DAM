package network;

import domain.model.CacheAuth;
import jakarta.inject.Inject;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private final CacheAuth auth;

    @Inject
    public AuthInterceptor(CacheAuth auth) {
        this.auth = auth;
    }

    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        Request newReq;
        if (auth.getJwt()!=null){
            newReq = req.newBuilder()
                    .header("Authorization", auth.getJwt()).build();
        } else {
            newReq = req;
        }

        Response res = chain.proceed(newReq);
        if (!res.isSuccessful()){
            if (res.code()!=401 && res.code()!=403){
                res.close();
                newReq = req.newBuilder()
                        .header("Authorization", Credentials.basic(auth.getUser(), auth.getPass())).build();
                res = chain.proceed(newReq);
            } else {
                return res;
            }
        }

        if (res.header("Authorization")!=null){
            auth.setJwt(res.header("Authorization"));
        }

        return res;



    }


}
