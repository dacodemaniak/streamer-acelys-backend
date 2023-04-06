package fr.aelion.streamer.dto.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String jwtToken;
    private List<String> roles;
}