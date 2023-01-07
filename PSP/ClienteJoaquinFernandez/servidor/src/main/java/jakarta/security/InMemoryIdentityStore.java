package jakarta.security;

import domain.services.LoginServ;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;


public class InMemoryIdentityStore implements IdentityStore {

    private final LoginServ serviciosUsuarios;

    @Override
    public int priority() {
        return 10;
    }


    @Inject
    public InMemoryIdentityStore(LoginServ serviciosUsuarios) {
        this.serviciosUsuarios = serviciosUsuarios;
    }


    @Override
    public CredentialValidationResult validate(Credential credential) {



        if (credential instanceof BasicAuthenticationCredential user) {


            HashSet<String> roles = new HashSet<>();
            roles.add("ADMIN");
            roles.add("USER");

            user.getPassword().getValue();
            return switch (user.getCaller()) {
                case "ADMIN" -> new CredentialValidationResult("ADMIN", Set.of("ADMIN"));// Collections.singleton("ADMIN"));
                case "USER" -> new CredentialValidationResult("USER", Collections.singleton("USER"));
                default -> INVALID_RESULT;
            };

        }
        return INVALID_RESULT;
    }

}
