package domainServ.error;

public class SinPermisoException extends RuntimeException{
    public SinPermisoException(String message) {
        super(message);
    }
}
