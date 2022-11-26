package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Newspaper;
import domain.services.NewspaperServ;
import java.util.List;

@Path(Const.NEWSPAPERS) // /newspapers
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
        Newspaper np = newspaperServ.add(newspaper);
        return Response.ok(np).build();
    }

    @PUT
    public Response updateNewspaper(Newspaper newspaper) {
        Newspaper np = newspaperServ.update(newspaper);
        return Response.ok(np).build();
    }

    @DELETE
    @Path(Const.ID_PARAM)
    public Response deleteNewspaper(@PathParam(Const.ID) int id) {
        newspaperServ.deleteNewspaper(id);
        //igual es por el return este
        //delete devuelve int
        return Response.ok().build();
    }

    @DELETE
    @Path("/confirm"+Const.ID_PARAM)
    public Response deleteConfirmed(@PathParam(Const.ID) int id) {
        newspaperServ.deleteConfirmed(id);
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
