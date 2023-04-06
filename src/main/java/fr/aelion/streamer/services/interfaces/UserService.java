package fr.aelion.streamer.services.interfaces;

import fr.aelion.streamer.entities.StreamerUser;


import java.util.Optional;

public interface UserService {
    Optional<StreamerUser> findByLoginAndPassword(String login, String password);
}
