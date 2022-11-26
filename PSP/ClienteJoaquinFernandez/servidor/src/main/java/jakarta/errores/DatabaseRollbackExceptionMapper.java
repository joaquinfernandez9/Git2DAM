package jakarta.errores;

public class DatabaseRollbackExceptionMapper extends RuntimeException {
    public DatabaseRollbackExceptionMapper(String message) {
        super(message);
    }

}
