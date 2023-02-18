package jakarta.errores;

import dao.domain.IntegrityException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;


@Provider
public class IntegrityExceptionMapper implements ExceptionMapper<IntegrityException> {

  public Response toResponse(IntegrityException exception) {
    ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
    return Response.status(Response.Status.CONFLICT).entity(apiError)
        .type(MediaType.APPLICATION_JSON_TYPE).build();
  }

}
