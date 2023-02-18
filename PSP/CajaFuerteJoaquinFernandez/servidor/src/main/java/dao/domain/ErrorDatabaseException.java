package dao.domain;

public class ErrorDatabaseException extends RuntimeException{
    public ErrorDatabaseException(String message) {
        super(message);
    }
}
