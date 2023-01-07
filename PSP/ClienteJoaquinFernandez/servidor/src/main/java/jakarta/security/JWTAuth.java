package jakarta.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;


@ApplicationScoped
public class JWTAuth implements HttpAuthenticationMechanism
{

    @Inject
    private InMemoryIdentityStore identity;


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse
            , HttpMessageContext httpMessageContext) throws AuthenticationException {
        CredentialValidationResult c = CredentialValidationResult.INVALID_RESULT;



        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] valores = header.split(" ");

            if (valores[0].equalsIgnoreCase("Basic")) {
                c = identity.validate(new BasicAuthenticationCredential(valores[1]));

                if (c.getStatus() == CredentialValidationResult.Status.VALID) {
                    httpServletRequest.getSession().setAttribute("USERLOGIN", c);
                    //generar token


                    //añadir al response


                }
            }
            else if (valores[0].equalsIgnoreCase("Bearer")) {



            }
            else if (valores[0].equalsIgnoreCase("Logout")) {
                httpServletRequest.getSession().removeAttribute("USERLOGIN");
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }

            //  else del bearer, bearer, bearer,beaer Ijust conat to wiat to vb warer

            //vlidarlo

        }
        else
        {
            if (httpServletRequest.getSession().getAttribute("USERLOGIN")!=null)
                c = (CredentialValidationResult)httpServletRequest.getSession().getAttribute("USERLOGIN");
        }

        if (c.getStatus().equals(CredentialValidationResult.Status.INVALID))
        {

            return httpMessageContext.doNothing();
        }
        return httpMessageContext.notifyContainerAboutLogin(c);
    }
}
