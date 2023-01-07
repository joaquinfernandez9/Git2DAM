package jakarta.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import model.Error;

import java.io.IOException;
import java.time.LocalDateTime;

@Log
@Provider
public class FilterLogin implements ContainerRequestFilter {
    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (request.getSession().getAttribute("Log") == null) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(Error.builder().message("Inicia sesion antes de continuar")
                            .fecha(LocalDateTime.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }
}
