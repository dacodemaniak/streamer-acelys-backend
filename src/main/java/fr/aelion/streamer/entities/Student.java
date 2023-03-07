package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;

    private String firstName;

    private String email;

    private String phoneNumber;

    private String login;

    private String password;
}
