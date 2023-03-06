package domainServ.error;

public class ContraseñaErronea extends RuntimeException{
    public ContraseñaErronea(String message) {
        super(message);
    }
}
