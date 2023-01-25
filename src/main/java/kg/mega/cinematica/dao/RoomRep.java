package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRep extends JpaRepository<Room, Long> {
}
