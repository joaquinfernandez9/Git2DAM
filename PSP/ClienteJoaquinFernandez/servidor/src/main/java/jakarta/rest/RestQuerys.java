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
        return querysServ.getAll();
    }

    @GET
    @Path(Const.QUERY_2_ID)
    public List<Reader> query2(@PathParam(Const.ID) int id){
        return querysServ.getOldest(id);
    }

    @GET
    @Path(Const.QUERY_3_DESCRIPTION)
    public List<QueryArticlesNewspaper> query3(@PathParam(Const.DESCRIPTION) String description){
        return querysServ.getAll(description);
    }

    @GET
    @Path(Const.QUERY_4_ID)
    public List<QueryArticleRating> query4(@PathParam(Const.ID) int id){
     return querysServ.getArticles(id);
    }

}
