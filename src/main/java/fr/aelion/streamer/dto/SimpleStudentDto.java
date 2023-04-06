package fr.aelion.streamer.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleStudentDto {
    private int id;
    private String lastName;
    private String firstName;
    private String email;

    private String phoneNumber;
}
