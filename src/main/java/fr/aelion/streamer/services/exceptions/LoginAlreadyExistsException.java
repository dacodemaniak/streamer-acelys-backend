package fr.aelion.streamer.services.exceptions;

public class LoginAlreadyExistsException extends Exception {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }

    public ErrorResponse reject() {
        var errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("login");

        return errorResponse;
    }
}
