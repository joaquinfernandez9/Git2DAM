package jakarta.errores;

import domain.modelo.CommonException;
import domain.modelo.DatabaseException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDateTime;

public class DatabaseExceptionMapper
        implements ExceptionMapper<DatabaseException> {

    @Override
    public Response toResponse(DatabaseException exception) {
        LogError logError = new LogError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(logError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
