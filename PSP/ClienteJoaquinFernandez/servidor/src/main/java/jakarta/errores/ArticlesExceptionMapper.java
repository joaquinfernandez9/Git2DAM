package jakarta.errores;

import domain.modelo.ArticlesException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ArticlesExceptionMapper
        implements ExceptionMapper<ArticlesException> {
    @Override
    public Response toResponse(ArticlesException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getArticles())
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
