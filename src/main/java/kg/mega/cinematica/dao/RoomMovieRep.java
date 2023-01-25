package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMovieRep extends JpaRepository<RoomMovie,Long> {
}
