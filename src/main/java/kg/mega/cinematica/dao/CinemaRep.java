package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRep extends JpaRepository<Cinema, Long> {
}
