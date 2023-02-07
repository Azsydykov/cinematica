package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CinemaRep extends JpaRepository<Cinema, Long> {

    @Query(value = "\n" +
            "select * from tb_room as r \n" +
            "INNER JOIN tb_cinema as c\n" +
            "on r.cinema_id=c.id\n" +
            "WHERE r.id=:roomId",nativeQuery = true)
    List<Cinema> findCinemaByRoomId(Long roomId);
}
