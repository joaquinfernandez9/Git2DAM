package jakarta.rest;

import jakarta.errores.LogError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Newspaper;
import services.NewspaperServ;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Const.NEWSPAPERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestNewspapers {

    private final NewspaperServ newspaperServ;

    @Inject
    public RestNewspapers(NewspaperServ newspaperServ) {
        this.newspaperServ = newspaperServ;
    }

    @GET
    public List<Newspaper> getNewspapers() {
        return newspaperServ.getAll();
    }

    @GET
    @Path(Const.ID_PARAM)
    public Newspaper getNewspaper(@PathParam(Const.ID) int id) {
        return newspaperServ.get(id);
    }

    @POST
    public Response addNewspaper(Newspaper newspaper) {
        int response = newspaperServ.add(newspaper);
        if (response == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateNewspaper(Newspaper newspaper) {
        AtomicReference<Response> r = new AtomicReference<>();
        newspaperServ.getAll().forEach(newspaper1 -> {
            if (newspaper1.getId() == newspaper.getId()) {
                newspaperServ.update(newspaper);
                r.set(Response.ok().entity(newspaper).build());
            }
        });
        if (r.get() == null) {
            logError(r);
        }
        return r.get();
    }

    @DELETE
    @Path(Const.ID)
    public Response deleteNewspaper(@PathParam(Const.ID) int id) {
        AtomicReference<Response> r = new AtomicReference<>();
        newspaperServ.getAll().forEach(newspaper -> {
            if (newspaper.getId() == id) {
                newspaperServ.deleteNewspaper(id);
                r.set(Response.ok().entity(newspaper).build());
            }
        });
        if (r.get() == null) {
            logError(r);
        }
        return r.get();
    }

    private void logError(AtomicReference<Response> r){
        LogError apiError = new LogError(Const.NO_NEWSPAPER_FOUND, LocalDateTime.now());
        r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
    }



}
