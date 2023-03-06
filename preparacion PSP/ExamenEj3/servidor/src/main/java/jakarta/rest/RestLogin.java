package jakarta.rest;


import dao.DaoLogin;
import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private DaoLogin dao;

    @Context
    private SecurityContext securityContext;

    @Inject
    public RestLogin(DaoLogin dao) {
        this.dao = dao;
    }

    @GET
    public List<Usuario> getAll () {
        return dao.getAll();
    }


    @POST
    @Path("/log")
    public Response login(){
        if (securityContext.getUserPrincipal()!=null){
            return Response.ok().entity(dao.comprobarUsuario(securityContext
                    .getUserPrincipal().getName())).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }




}
