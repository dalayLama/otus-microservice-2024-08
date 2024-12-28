package org.otus.microservice.userprofile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication extends AbstractAuthenticationToken implements Authentication {

    private final String token;

    private final JwtPrincipal principal;

    public JwtAuthentication(String token, JwtPrincipal principal) {
        this(Collections.emptyList(), token, principal);
    }

    public JwtAuthentication(Collection<? extends GrantedAuthority> authorities,
                             String token,
                             JwtPrincipal principal) {
        super(authorities);
        this.token = token;
        this.principal = principal;
        this.setAuthenticated(principal != null);
    }

    public static JwtAuthentication authenticationRequest(String token) {
        return new JwtAuthentication(token, null);
    }

    public static JwtAuthentication authenticated(String token, JwtPrincipal principal) {
        return new JwtAuthentication(token, principal);
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public JwtPrincipal getPrincipal() {
        return principal;
    }

}
