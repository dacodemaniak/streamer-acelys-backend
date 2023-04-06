package fr.aelion.streamer.repositories;

import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.StreamerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreamerUserRepository extends JpaRepository<StreamerUser, Integer> {
    public Optional<StreamerUser> findByEmail(String email);
    public Optional<StreamerUser> findByLogin(String login);

    public Optional<StreamerUser> findById(int id);
    @Query("SELECT s.id id, s.lastName lastName, s.firstName firstName, s.email email FROM StreamerUser s")
    public List<SimpleStudentProjection> getSimpleStudents();

    @Query("SELECT s FROM StreamerUser s WHERE s.login = :login AND s.password = :password")
    public Optional<StreamerUser> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    @Query("SELECT s FROM StreamerUser s WHERE email = :email OR login = :login")
    public StreamerUser findByEmailOrLogin(@Param("email") String email, @Param("login") String login);

    @Query(
            value="SELECT a.* FROM account a WHERE a.email = :email OR a.login = :login",
            nativeQuery = true
    )
    public StreamerUser nativeByEmailOrLogin(@Param("email") String email, @Param("login") String login);
}
