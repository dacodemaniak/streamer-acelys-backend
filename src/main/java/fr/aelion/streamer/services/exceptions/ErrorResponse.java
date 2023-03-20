package fr.aelion.streamer.services.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String reason;
    private String attribute;
}
