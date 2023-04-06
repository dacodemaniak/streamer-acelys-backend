package fr.aelion.streamer.errors.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    private static final long serialVersionUID = 1L;
    public JwtTokenMalformedException(String message) {
        super(message);
    }
}
