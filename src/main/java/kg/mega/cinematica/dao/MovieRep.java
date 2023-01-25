package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRep extends JpaRepository<Movie,Long> {
}
