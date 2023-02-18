package jakarta.security;

import domain.User;
import dao.domain.services.ServicesLogin;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;

@Singleton
public class BDIdentityStore implements IdentityStore {
    private final ServicesLogin servicesLogin;

    @Inject
    public BDIdentityStore(ServicesLogin servicesLogin) {
        this.servicesLogin = servicesLogin;
    }


    @Override
    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result = CredentialValidationResult.INVALID_RESULT;
        BasicAuthenticationCredential user = (BasicAuthenticationCredential) credential;
        User username = servicesLogin.login(user.getCaller(), String.valueOf(user.getPassword().getValue()));
        if (username != null) {
            result = new CredentialValidationResult(username.getUsername(), Collections.singleton(username.getRole()));
        }
        return result;

    }
}
