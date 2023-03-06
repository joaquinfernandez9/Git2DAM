package jakarta.security;

import dao.DaoLogin;
import domain.modelo.Usuario;
import domainServ.error.NotFoundException;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

public class MyIdentityStore implements IdentityStore {

    private final DaoLogin daoLogin;

    @Inject
    public MyIdentityStore(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        BasicAuthenticationCredential user = (BasicAuthenticationCredential) credential;
        try {
            Usuario username = daoLogin.validate(user.getCaller(), user.getPasswordAsString());
            return new CredentialValidationResult(username.getUsuario(), username.getRoles());
        } catch (NotFoundException e) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }


}
