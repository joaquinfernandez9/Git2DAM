package jakarta.rest;

import jakarta.errores.CommonExceptionMapper;
import jakarta.errores.DatabaseExceptionMapper;
import jakarta.errores.LogError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Reader;
import domain.services.ReaderServ;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Const.READERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestReader {

    private final ReaderServ readerServ;

    @Inject
    public RestReader(ReaderServ readerServ) {
        this.readerServ = readerServ;
    }

    @GET
    public List<Reader> getReaders() {
        return readerServ.getAll(-1, null);
    }

    @GET
    @Path(Const.ID_PARAM)
    public Reader getReader(@PathParam(Const.ID) int id) {
        return readerServ.get(id);
    }


    @DELETE
    @Path(Const.ID_PARAM)
    public Response deleteReader(@PathParam(Const.ID) int id) {
        readerServ.deleteReader(id);
        return Response.noContent().build();
    }

    @POST
    public Response addReader(Reader reader) {
        Reader response = readerServ.add(reader);
        return Response.ok(response).build();
    }

    @PUT
    public Response updateReader(Reader reader) {
        Reader response = readerServ.update(reader);
        return Response.ok(response).build();
    }

    @POST
    @Path(Const.APPEND_ARTICLE_ID_RATING)
    public Response appendArticle(@PathParam(Const.ID) int id, @PathParam(Const.RATING) int rating, Reader reader) {
        AtomicReference<Response> r = new AtomicReference<>();
        int repsonse = readerServ.appendReadArticle(reader, id, rating);
        if (repsonse == 1) {
            r.set(Response.status(Response.Status.OK).build());
        } else {
            r.set(Response.status(Response.Status.BAD_REQUEST).build());
        }
        return r.get();
    }

}
