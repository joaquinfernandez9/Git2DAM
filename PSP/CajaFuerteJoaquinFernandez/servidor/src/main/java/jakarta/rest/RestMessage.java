package jakarta.rest;

import domain.Message;
import dao.domain.services.ServicesMessage;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestMessage {
    private final ServicesMessage servicesMessage;

    @Inject
    public RestMessage(ServicesMessage servicesMessage) {
        this.servicesMessage = servicesMessage;
    }

    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") int id) {
        return servicesMessage.get(id);
    }

    @GET
    @Path("/all/{id}")
    public Response getAll(@PathParam("id") int idFolder) {
        return Response.ok(servicesMessage.getAll(idFolder)).build();
    }

    @POST
    @Path("/add")
    public Response addMessage(Message message) {
        servicesMessage.insert(message);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response updateMessage(Message message) {
        servicesMessage.update(message);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMessage(@PathParam("id") int id) {
        servicesMessage.delete(id);
        return Response.noContent().build();
    }




}
