package org.otus.microservice.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import reactor.core.publisher.Mono;

public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        DecodedJWT jwt = JWT.decode(token);
        Algorithm algorithm = Algorithm.HMAC256("otus");
        try {
            algorithm.verify(jwt);
        } catch (SignatureVerificationException e) {
            throw new JwtAuthenticationException("Invalid JWT", e);
        }
        return Mono.just(JwtAuthenticationToken.authenticated(token, jwt));
    }

    private static class JwtAuthenticationException extends AuthenticationException {

        public JwtAuthenticationException(String msg) {
            super(msg);
        }

        public JwtAuthenticationException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

}
