package jakarta.security;

import config.Configuration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

import static config.Const.*;

@ApplicationScoped
public class JWTAuth implements HttpAuthenticationMechanism {
    private BDIdentityStore identity;
    private final Configuration config;
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Inject
    public JWTAuth(BDIdentityStore identity, Configuration config) {
        this.identity = identity;
        this.config = config;
    }

    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext){
        CredentialValidationResult result = CredentialValidationResult.INVALID_RESULT;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] parts = header.split(BLANK);
            if (parts[0].equalsIgnoreCase(BASIC)) {
                result = identity.validate(new BasicAuthenticationCredential(parts[1]));
                if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                    String jws = Jwts.builder()
                            .setSubject(SERVER)
                            .setIssuer(YO)
                            .setExpiration(Date.from(LocalDateTime.now().plusMinutes(config.getTime()).atZone(ZoneId.systemDefault()).toInstant()))
                            .claim(USER, result.getCallerPrincipal().getName())
                            .claim(ROLES, result.getCallerGroups())
                            .signWith(key).compact();
                    response.addHeader(HttpHeaders.AUTHORIZATION, BEARER + BLANK + jws);
                }
            } else if (parts[0].equalsIgnoreCase(BEARER)) {
                Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(parts[1]);
                result = new CredentialValidationResult(jws.getBody().get(USER).toString(), Collections.singleton(jws.getBody().get(ROLES).toString()));
            }
        } else {
            result = CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        if (!result.getStatus().equals(CredentialValidationResult.Status.VALID)) {
            return httpMessageContext.doNothing();
        }
        return httpMessageContext.notifyContainerAboutLogin(result);
    }
}
