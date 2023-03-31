package fr.aelion.streamer.repositories;

import fr.aelion.streamer.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}
