package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String role;

    @OneToMany(mappedBy = "role")
    Set<StreamerUser> users = new HashSet<>();
}