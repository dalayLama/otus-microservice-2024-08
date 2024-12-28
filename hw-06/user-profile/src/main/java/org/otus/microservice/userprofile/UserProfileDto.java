package org.otus.microservice.userprofile;

public record UserProfileDto(Long id, String login, String nickname) {

    public UserProfileDto(UserProfile userProfile, JwtPrincipal jwtPrincipal) {
        this(userProfile.getId(), jwtPrincipal.login(), userProfile.getNickname());
    }

    public UserProfileDto(JwtPrincipal jwtPrincipal) {
        this(jwtPrincipal.id(), jwtPrincipal.login(), jwtPrincipal.login());
    }

}
