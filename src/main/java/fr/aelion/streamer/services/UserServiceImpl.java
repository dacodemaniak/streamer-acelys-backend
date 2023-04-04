package fr.aelion.streamer.services;

import fr.aelion.streamer.entities.User;
import fr.aelion.streamer.repositories.UserRepository;
import fr.aelion.streamer.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
