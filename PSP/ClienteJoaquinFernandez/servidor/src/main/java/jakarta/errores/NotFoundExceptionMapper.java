package jakarta.errores;

public class NotFoundExceptionMapper extends RuntimeException {
    public NotFoundExceptionMapper(String message) {
        super(message);
    }

}
