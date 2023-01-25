package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRep extends JpaRepository<Seat,Long> {
}
