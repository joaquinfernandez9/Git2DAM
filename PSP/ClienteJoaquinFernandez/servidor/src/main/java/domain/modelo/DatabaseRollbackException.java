package domain.modelo;

public class DatabaseRollbackException extends RuntimeException {

    public DatabaseRollbackException(String message) {
        super(message);
    }

}
