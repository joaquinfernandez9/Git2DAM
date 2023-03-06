package jakarta.rest;

import dao.DaoInformes;
import domain.modelo.Informe;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.servlet.http.PushBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/informes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestInformes {
    private DaoInformes dao;
    @Context
    private SecurityContext securityContext;

    @Inject
    public RestInformes(DaoInformes dao) {
        this.dao = dao;
    }

    @GET
    @RolesAllowed({"informe"})
    public List<Informe> getInformes(){
        return dao.getInformes(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/{nombreInforme}")
    @RolesAllowed({"informe"})
    public Informe getInforme(@PathParam("nombreInforme") String nombreInforme){
        return dao.getInforme(securityContext.getUserPrincipal().getName(), nombreInforme);
    }

    @POST
    @RolesAllowed({"ESPIA"})
    @Path("/add")
    public Informe add(Informe informe){
        return dao.addInforme(informe);
    }




}




