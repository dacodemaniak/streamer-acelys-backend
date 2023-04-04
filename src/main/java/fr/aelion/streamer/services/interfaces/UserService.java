package fr.aelion.streamer.services.interfaces;

import fr.aelion.streamer.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByLoginAndPassword(String login, String password);
}
