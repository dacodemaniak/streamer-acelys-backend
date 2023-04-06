package fr.aelion.streamer.repositories;

import fr.aelion.streamer.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
