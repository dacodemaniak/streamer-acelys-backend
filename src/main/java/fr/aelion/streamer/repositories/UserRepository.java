package fr.aelion.streamer.repositories;

import fr.aelion.streamer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
