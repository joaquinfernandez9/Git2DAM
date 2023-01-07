package jakarta.rest;


import domain.services.LoginServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Code;
import model.Error;
import model.Login;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

@Path("/private")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private final LoginServ serv;

    @Inject
    public RestLogin(LoginServ serv) {
        this.serv = serv;
    }

    @Context
    HttpServletRequest req;

    @GET
    @Path("/login")
    public Response login(Login log){
        Either<String, Login> login = serv.login(log.getUser(), log.getPassword());
        AtomicReference<Response> r = new AtomicReference<>();
        if (login.isLeft()){
            r.set(Response.status(Response.Status.CONFLICT).entity(
                    new Error(login.getLeft(), LocalDateTime.now())).build());
        } else {
            req.getSession().setAttribute("Log", true);
            r.set(Response.ok().entity(login.get()).build());
        }
        return r.get();
    }


    @POST
    @Path("/register")
    public Response register(Login log) throws MessagingException {
        AtomicReference<Response> r = new AtomicReference<>();
        MandarMail mail = new MandarMail();
        String randomBytes = Utils.randomBytes();

        Code code = new Code(randomBytes, Time.valueOf(LocalTime.now().plusMinutes(5)), log.getUser());
        Either<String, Login> register = serv.register(log, code);
        if (register.isLeft()) {
            if (register.getLeft().equals("Tiempo acabado")){
                mail.generateAndSendEmail(log.getEmail(),
                        "<html>Este es tu nuevo codigo: <a href=\"http://localhost:8080/servidor-1.0-SNAPSHOT/activation?code=" +
                                randomBytes + "\" >marca</a> " + randomBytes + "</html>"
                        , "Codigo verificacion");
            }
            r.set(Response.status(Response.Status.CONFLICT).entity(register.getLeft()).build());
        } else {
            mail.generateAndSendEmail(log.getEmail(),
                    "<html>Este es tu codigo: <a href=\"http://localhost:8080/servidor-1.0-SNAPSHOT/activation?code=" +
                            randomBytes + "\" >marca</a> " + randomBytes + ". Codigo valido hasta: "+ code.getExpiration_date().toLocalTime() +"</html>"
                    , "Codigo verificacion");
            r.set(Response.ok().entity(register.get()).build());
        }

        return r.get();
    }

}
