package jakarta.errores;

import jakarta.ws.rs.ext.Provider;

@Provider
public class DatabaseIntegrityExceptionMapper extends RuntimeException {
    public DatabaseIntegrityExceptionMapper(String message) {
        super(message);
    }
}
