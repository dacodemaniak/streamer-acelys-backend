package fr.aelion.streamer.dto.request;

import fr.aelion.streamer.entities.UserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Lastname cannot be null")
    private String lastName;

    private String firstName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "email passed is not a valid email")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "login cannot be null")
    @Size(min = 8, message="Login must have at least 8 chars")
    private String login;

    @NotBlank(message = "password cannot be null")
    @Size(min = 8, message="Password must have at least 8 chars")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    private UserRole role;
}
