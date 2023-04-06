package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.UserRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDto {
    private int id;
    private String login;
    private String lastName;
    private String firstName;
    private String email;

    private String phoneNumber;

    private UserRoleDto role;
}
