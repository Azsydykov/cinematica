package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Room;
import kg.mega.cinematica.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRep extends JpaRepository<Room, Long> {

}
