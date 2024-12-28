package org.otus.microservice.userprofile;

import java.security.Principal;

public record JwtPrincipal(
        Long id,
        String login
) implements Principal {

    @Override
    public String getName() {
        return login();
    }

}
