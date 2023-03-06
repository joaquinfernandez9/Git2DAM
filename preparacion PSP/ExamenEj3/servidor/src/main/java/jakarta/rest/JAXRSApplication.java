package jakarta.rest;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@DeclareRoles({"ratones", "informe", "curioso", "biologo", "ESPIA", "NIVEL 1", "NIVEL 2"})
public class JAXRSApplication extends Application {
}
