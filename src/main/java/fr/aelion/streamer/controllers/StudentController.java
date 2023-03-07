package fr.aelion.streamer.controllers;

import fr.aelion.streamer.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import fr.aelion.streamer.services.StudentService;

@RestController
@RequestMapping("api/v1/students") // http://127.0.0.1:8080/api/v1/students
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    @CrossOrigin
    public List<Student> findAll() {
        return studentService.findAll();
    }
}
