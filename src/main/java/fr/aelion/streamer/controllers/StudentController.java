package fr.aelion.streamer.controllers;

import fr.aelion.streamer.dto.SignupMessage;
import fr.aelion.streamer.dto.SimpleUserDto;
import fr.aelion.streamer.dto.request.UserRequestDto;
import fr.aelion.streamer.entities.StreamerUser;

import fr.aelion.streamer.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import fr.aelion.streamer.services.StudentService;

@RestController
@RequestMapping("api/v1/students") // http://127.0.0.1:8080/api/v1/students
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserAuthService userAuthService;
    @GetMapping
    public List<StreamerUser> findAll() {
        return studentService.findAll();
    }

    @GetMapping("simple")
    public List<SimpleUserDto> findSimpleStudents() {
        return studentService.findSimpleStudents();
    }

    @GetMapping("{id}") // GET http://127.0.0.1:5000/api/v1/students/1
    public ResponseEntity<?> findOne(@PathVariable int id) {
        try {
            return ResponseEntity.ok(studentService.findOne(id));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>( "Student with " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("signup")
    public SignupMessage signup(@RequestBody UserRequestDto request) {
        this.userAuthService.add(request);
        SignupMessage message = new SignupMessage("User was successfully registred");
        return message;
    }

}
