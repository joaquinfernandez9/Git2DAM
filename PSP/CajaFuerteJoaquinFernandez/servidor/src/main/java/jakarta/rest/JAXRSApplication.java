package jakarta.rest;

import config.Const;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath(Const.API)
@DeclareRoles({"[admin]", "[user]"})
public class JAXRSApplication {}
