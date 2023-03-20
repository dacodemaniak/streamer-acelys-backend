package fr.aelion.streamer.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentDto {
    @NotBlank
    private String lastName;

    private String firstName;

    @NotNull
    @Email
    private String email;

    private String phoneNumber;

    @NotBlank
    private String login;

    @NotBlank
    //@Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/")
    private String password;
}
