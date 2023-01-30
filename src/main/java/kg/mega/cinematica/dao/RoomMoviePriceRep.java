package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Price;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMoviePriceRep extends JpaRepository<RoomMoviePrice,Long> {
}
