package fr.aelion.streamer.dto.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String jwtToken;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private List<String> roles;
}