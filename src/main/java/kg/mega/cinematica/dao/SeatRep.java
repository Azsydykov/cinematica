package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRep extends JpaRepository<Seat,Long> {
    @Query(value = "select * from tb_seat as s \n" +
            "WHERE s.room_id=:roomId",nativeQuery = true)
    List<Seat> findSeatsByRoomId(Long roomId);
}
