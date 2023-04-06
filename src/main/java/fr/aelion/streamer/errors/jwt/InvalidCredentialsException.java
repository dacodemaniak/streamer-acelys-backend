package fr.aelion.streamer.errors.jwt;

public class InvalidCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCredentialsException() {
        super("Credentials provided was invalid");
    }
}
