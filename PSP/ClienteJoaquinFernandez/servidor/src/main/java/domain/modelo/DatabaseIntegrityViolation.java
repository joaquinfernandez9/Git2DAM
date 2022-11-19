package domain.modelo;

public class DatabaseIntegrityViolation extends RuntimeException {

    public DatabaseIntegrityViolation(String message) {
        super(message);
    }

}
