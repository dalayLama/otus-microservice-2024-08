package org.otus.microservice.auth;

public record UserRequest(
        String login,
        String password
) {}
