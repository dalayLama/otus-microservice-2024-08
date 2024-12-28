package org.otus.microservice.userprofile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication authenticationRequest = (JwtAuthentication) authentication;

        String token = authenticationRequest.getCredentials();

        DecodedJWT decodedJwt = JWT.decode(token);
        JwtPrincipal principal = new JwtPrincipal(
                Long.parseLong(decodedJwt.getSubject()),
                decodedJwt.getClaim("login").asString()
        );

        return JwtAuthentication.authenticated(token, principal);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }

}
