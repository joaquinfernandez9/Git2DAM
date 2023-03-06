package jakarta.error;

import domainServ.error.ContraseñaErronea;
import domain.modelo.error.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDate;

@Provider
public class ContraseñaErroneaMapper implements ExceptionMapper<ContraseñaErronea> {
    @Override
    public Response toResponse(ContraseñaErronea exception) {
        ApiError error = new ApiError(exception.getMessage(), LocalDate.now());
        return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
