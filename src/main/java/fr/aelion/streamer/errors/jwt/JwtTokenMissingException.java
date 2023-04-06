package fr.aelion.streamer.errors.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public JwtTokenMissingException() {
        super("Token was missing");
    }
}
