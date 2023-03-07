package fr.aelion.streamer.repositories;

import fr.aelion.streamer.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {}
