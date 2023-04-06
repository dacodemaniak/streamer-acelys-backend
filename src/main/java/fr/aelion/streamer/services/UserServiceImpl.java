package fr.aelion.streamer.services;

import fr.aelion.streamer.entities.StreamerUser;
import fr.aelion.streamer.repositories.StreamerUserRepository;
import fr.aelion.streamer.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    StreamerUserRepository repository;
    @Override
    public Optional<StreamerUser> findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
