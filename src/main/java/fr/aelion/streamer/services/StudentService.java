package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.SimpleUserDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;

import fr.aelion.streamer.entities.StreamerUser;
import fr.aelion.streamer.entities.UserRole;
import fr.aelion.streamer.repositories.StreamerUserRepository;
import fr.aelion.streamer.repositories.UserRoleRepository;
import fr.aelion.streamer.services.exceptions.EmailAlreadyExistsException;
import fr.aelion.streamer.services.exceptions.LoginAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
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
        return repository.findAll()
                .stream()
                .filter(ur -> ur.getRole() == "Student")
                .map(ur -> ur.getUsers())
                .map(s -> {
                    return modelMapper.map(s, SimpleUserDto.class);
                })
                .collect(Collectors.toList());
    }

    public SimpleUserDto findOne(int id) {
        return studentRepository.findById(id)
                .map(s -> modelMapper.map(s, SimpleUserDto.class))
                .orElseThrow();
    }
}
