package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="account")
@Getter
@Setter
public class StreamerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName;

    private String firstName;

    private String email;

    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne(targetEntity = UserRole.class)
    @JoinColumn(name = "role_id")
    private UserRole role;
}
