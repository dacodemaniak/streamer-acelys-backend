package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.SignupMessage;
import fr.aelion.streamer.dto.SimpleUserDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;

import fr.aelion.streamer.dto.request.UserRequestDto;
import fr.aelion.streamer.entities.StreamerUser;
import fr.aelion.streamer.entities.UserRole;
import fr.aelion.streamer.repositories.StreamerUserRepository;
import fr.aelion.streamer.repositories.UserRoleRepository;
import fr.aelion.streamer.services.exceptions.EmailAlreadyExistsException;
import fr.aelion.streamer.services.exceptions.LoginAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private UserRoleRepository repository;

    @Autowired
    StreamerUserRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<StreamerUser> findAll() {
        return repository.findAll()
                .stream()
                .filter(ur -> ur.getRole() == "Student")
                .map(ur -> ur.getUsers())
                .map(u -> modelMapper.map(u, StreamerUser.class))
                .collect(Collectors.toList());
    }

    public List<SimpleUserDto> findSimpleStudents() {
        var list = new ArrayList<SimpleUserDto>();
        repository.findAll()
                .stream()
                .filter(ur -> ur.getRole().equals("Student"))
                .collect(Collectors.toList())
                .forEach(ur -> {
                    ur.getUsers().forEach(u -> {
                        list.add(modelMapper.map(u, SimpleUserDto.class));
                    });
                });
        return list;
    }

    public SimpleUserDto findOne(int id) {
        return studentRepository.findById(id)
                .map(s -> modelMapper.map(s, SimpleUserDto.class))
                .orElseThrow();
    }


}
