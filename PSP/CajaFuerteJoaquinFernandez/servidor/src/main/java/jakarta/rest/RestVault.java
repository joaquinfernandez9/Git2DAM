package jakarta.rest;

import domain.Vault;
import dao.domain.services.ServicesVault;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/folder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestVault {
    private final ServicesVault servicesVault;

    @Inject
    public RestVault(ServicesVault servicesVault) {
        this.servicesVault = servicesVault;
    }

    @GET
    public Response getAll() {
        return Response.ok(servicesVault.getAll()).build();
    }

    @POST
    @Path("/add")
    public Response addFolder(Vault vault) {
        servicesVault.insert(vault);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete")
    public Response deleteFolder(Vault vault) {
        servicesVault.delete(vault);
        return Response.noContent().build();
    }
}
