package jakarta.rest;

import config.Const;
import domain.User;
import dao.domain.services.ServicesLogin;
import jakarta.annotation.security.RolesAllowed;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.time.LocalDateTime;

@Path(Const.CREDENTIALS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    @Context
    HttpServletRequest request;
    @Context
    SecurityContext securityContext;
    private final ServicesLogin servicesLogin;

    @Inject
    public RestLogin(ServicesLogin servicesLogin) {
        this.servicesLogin = servicesLogin;
    }

    @POST
    @Path(Const.LOGIN)
    public User doLogin() {
        String header = request.getHeader("Authorization");
        String[] values = header.split(" ");
        String base64Credentials = values[1];
        String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
        String[] userPass = credentials.split(":", 2);
        return servicesLogin.login(userPass[0], userPass[1]);
    }

    @POST
    @Path(Const.REGISTER)
    public User doRegister(User user) {
        return servicesLogin.register(user);
    }

    @GET
    @Path(Const.LOGOUT)
    public Response logout(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        if (securityContext.getUserPrincipal() != null) {
            servicesLogin.scLogout(authorization);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError((String) request.getAttribute(Const.ERROR_LOGIN), LocalDateTime.now()))
                    .build();
        }
    }

}
