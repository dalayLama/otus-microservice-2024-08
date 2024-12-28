package org.otus.microservice.gateway;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken implements Authentication {

    private final String token;

    private final DecodedJWT decodedJWT;

    public JwtAuthenticationToken(String token) {
        this(token, null);
    }

    public JwtAuthenticationToken(String token,
                                  DecodedJWT decodedJWT) {
        super(Collections.emptyList());
        this.token = token;
        this.decodedJWT = decodedJWT;
        setAuthenticated(decodedJWT != null);
    }

    public static Authentication newAuthenticationRequest(String token) {
        return new JwtAuthenticationToken(token);
    }

    public static Authentication authenticated(String token, DecodedJWT jwt) {
        return new JwtAuthenticationToken(token, jwt);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return decodedJWT;
    }

}
