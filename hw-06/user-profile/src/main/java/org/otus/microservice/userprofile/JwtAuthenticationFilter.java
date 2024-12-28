package org.otus.microservice.userprofile;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        getToken(request).ifPresent(this::authenticate);
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        JwtAuthentication authentication = JwtAuthentication.authenticationRequest(token);
        Authentication authenticationResult = authenticationManager.authenticate(authentication);

        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authenticationResult);
        this.securityContextHolderStrategy.setContext(context);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .map(val -> val.substring(BEARER_PREFIX.length()));
    }

}
