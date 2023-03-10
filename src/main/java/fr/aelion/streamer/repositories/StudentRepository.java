package fr.aelion.streamer.repositories;

import fr.aelion.streamer.dto.SimpleStudentDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByEmail(String email);

    @Query("SELECT s.id id, s.lastName lastName, s.firstName firstName, s.email email FROM Student s")
    public List<SimpleStudentProjection> getSimpleStudents();
}
