package dao.api.utils;

import jakarta.inject.Inject;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {


    private final CacheAuthorization ca;

    @Inject
    public AuthorizationInterceptor(CacheAuthorization ca) {
        this.ca = ca;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request().newBuilder()
                .addHeader("Protocol-Request", "HTTP/2.0")
                .addHeader("Accept", "application/json")
                .method(chain.request().method(), chain.request().body())
                .build();
        Request request;

        // Send request with the cached JWT if present
        if (ca.getJwtAuth() != null) {
            request = original.newBuilder()
                    .header("Authorization", ca.getJwtAuth())
                    .build();
        } else {
            request = original;
        }

        // Save the JWT in the cache
        Response response = chain.proceed(request);
        if (response.header("Authorization") != null)
            ca.setJwtAuth(response.header("Authorization"));

        // Re-authenticate if the token is expired
        String tokenExpiredHeader = response.header("Token expired");
        if (tokenExpiredHeader != null && tokenExpiredHeader.equals("true")) {
            response.close();
            request = original.newBuilder()
                    .header("Authorization", Credentials.basic(ca.getUser(), ca.getPassword())).build();
            response = chain.proceed(request);
            if (response.header("Authorization") != null)
                ca.setJwtAuth(response.header("Authorization"));
            return response;
        }

        return response;
    }
}
