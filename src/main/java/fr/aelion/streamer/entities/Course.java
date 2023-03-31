package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="course")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String objective;

    @OneToMany(mappedBy = "course")
    private Set<Module> modules;

    public Course() {
        this.createdAt = LocalDate.now();
    }
}
