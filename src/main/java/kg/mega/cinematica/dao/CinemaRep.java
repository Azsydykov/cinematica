package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Cinema;
import kg.mega.cinematica.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CinemaRep extends JpaRepository<Cinema, Long> {

    @Query(value = "\n" +
            "select * from tb_room as r \n" +
            "INNER JOIN tb_cinema as c\n" +
            "on r.cinema_id=c.id\n" +
            "WHERE r.id=:roomId",nativeQuery = true)
    List<Cinema> findCinemaByRoomId(Long roomId);

    @Query(value = "select * from tb_cinema LIMIT :limit OFFSET :offset",nativeQuery = true)
    List<Cinema> findAllCinemas(int limit, int offset);
}
