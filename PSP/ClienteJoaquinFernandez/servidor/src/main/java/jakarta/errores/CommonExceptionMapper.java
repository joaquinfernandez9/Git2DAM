package jakarta.errores;

import domain.modelo.CommonException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class CommonExceptionMapper implements ExceptionMapper<CommonException> {

    @Override
    public Response toResponse(CommonException exception) {
        LogError logError = new LogError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(logError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
