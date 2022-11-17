package jakarta.rest;

import jakarta.errores.LogError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Reader;
import services.ReaderServ;

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
        List<Reader> readers = readerServ
                .getAll(-1, null)
                .getOrElseGet(throwable -> null);
        if (readers == null) {
            logError();
        }
        return readers;
    }

    @GET
    @Path(Const.ID_PARAM)
    public Response getReader(@PathParam(Const.ID) int id) {

        AtomicReference<Response> r = new AtomicReference<>();
        Reader reader = readerServ.get(id).getOrNull();
        if (reader != null) {
            r.set(Response.ok().entity(reader).build());
        } else {
            apiErrorResponse(r);
        }
        return r.get();
    }


    @DELETE
    @Path(Const.ID_PARAM)
    public Response deleteReader(@PathParam(Const.ID) int id) {
        AtomicReference<Response> r = new AtomicReference<>();
        Reader reader = readerServ.get(id).getOrNull();
        if (reader != null) {
            int response = readerServ.deleteReader(id);
            if (response == 1) {
                r.set(Response.status(Response.Status.OK).build());
            } else {
                r.set(Response.status(Response.Status.BAD_REQUEST).build());
            }
            r.set(Response.ok().entity(reader).build());
        } else {
            apiErrorResponse(r);
        }
        return r.get();
    }

    @POST
    public Response addReader(Reader reader) {
        AtomicReference<Response> r = new AtomicReference<>();
        int response = readerServ.add(reader);
        if (response == 1) {
            r.set(Response.status(Response.Status.CREATED).build());
        } else {
            r.set(Response.status(Response.Status.BAD_REQUEST).build());
        }
        return r.get();
    }

    @PUT
    public Response updateReader(Reader reader) {
        AtomicReference<Response> r = new AtomicReference<>();
        int response = readerServ.update(reader);
        if (response == 1) {
            r.set(Response.status(Response.Status.OK).build());
        } else {
            r.set(Response.status(Response.Status.BAD_REQUEST).build());
        }
        return r.get();
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

    private void logError() {
        LogError apiError = new LogError(Const.NO_READERS_FOUND, LocalDateTime.now());
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
    }

    private void apiErrorResponse(AtomicReference<Response> r) {
        LogError apiError = new LogError(Const.READER_IS_NULL, LocalDateTime.now());
        r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
    }


}
