package fr.aelion.streamer.services;

import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> findAll() {
        List<Student> students = repository.findAll();
        return students;
    }

    public Student add(Student student) throws Exception {

       Student anyStudent = repository.findByEmail(student.getEmail());
        if (anyStudent != null) {
            throw new Exception("Student already exists");
        }

        student = (Student) repository.save(student);

        return student;
    }
}
