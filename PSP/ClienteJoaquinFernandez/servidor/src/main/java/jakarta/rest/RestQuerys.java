package jakarta.rest;


import jakarta.errores.LogError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;
import domain.services.QuerysServ;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Path(Const.QUERYS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestQuerys {


    private final QuerysServ querysServ;

    @Inject
    public RestQuerys(QuerysServ querysServ) {
        this.querysServ = querysServ;
    }

    @GET
    @Path(Const.QUERY_1)
    public List<QueryDescNumber> query1(){
        List<QueryDescNumber> list = querysServ.getAll();
        if (list == null) {
            throwError();
            return Collections.emptyList();
        } else {
            return list;
        }
    }

    @GET
    @Path(Const.QUERY_2_ID)
    public List<Reader> query2(@PathParam(Const.ID) int id){
        List<Reader> readersList = querysServ.getOldest(id);
        if (readersList == null){
            throwError();
            return Collections.emptyList();
        } else {
            return readersList;
        }
    }

    @GET
    @Path(Const.QUERY_3_DESCRIPTION)
    public List<QueryArticlesNewspaper> query3(@PathParam(Const.DESCRIPTION) String description){
        List<QueryArticlesNewspaper> list = querysServ.getAll(description);
        if (list.isEmpty()) {
            throwError();
            return Collections.emptyList();
        } else {
            return list;
        }
    }

    @GET
    @Path(Const.QUERY_4_ID)
    public List<QueryArticleRating> query4(@PathParam(Const.ID) int id){
        List<QueryArticleRating> list = querysServ.getArticles(id);
        if (list.isEmpty()) {
            throwError();
            return Collections.emptyList();
        } else {
            return list;
        }
    }

    private void throwError(){
        LogError apiError = new LogError(Const.NO_DATA_FOUND, LocalDateTime.now());
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
    }
}
