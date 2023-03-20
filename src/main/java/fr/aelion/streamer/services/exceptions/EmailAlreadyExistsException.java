package fr.aelion.streamer.services.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public ErrorResponse reject() {
        var errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setReason(this.getMessage());
        errorResponse.setAttribute("email");

        return errorResponse;
    }
}
