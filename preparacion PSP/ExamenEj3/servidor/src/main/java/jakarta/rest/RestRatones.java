package jakarta.rest;

import dao.DaoRatones;
import domain.modelo.Raton;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/ratones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRatones {
    private final DaoRatones dao;

    @Inject
    public RestRatones(DaoRatones dao) {
        this.dao = dao;
    }



    @GET
    @RolesAllowed({"curioso", "biologo"})
    public List<Raton> getAll(){
        return dao.getAll();
    }

    @POST
    @RolesAllowed({"biologo"})
    public Raton add(Raton raton) {
        return dao.add(raton);
    }

}
