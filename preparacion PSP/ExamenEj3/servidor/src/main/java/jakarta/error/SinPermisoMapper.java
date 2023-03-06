package jakarta.error;

import domainServ.error.SinPermisoException;
import domain.modelo.error.ApiError;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDate;

public class SinPermisoMapper implements ExceptionMapper<SinPermisoException> {
    @Override
    public Response toResponse(SinPermisoException exception) {
        ApiError error = new ApiError(exception.getMessage(), LocalDate.now());
        return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
