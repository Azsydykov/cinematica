package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomMovieRep extends JpaRepository<RoomMovie,Long> {


    @Query(value = "SELECT * from tb_room_movie as rm WHERE rm.movie_id=:movieId",nativeQuery = true)
    List<RoomMovie> getAllByMovieId(Long movieId);

    @Query(value = "SELECT * from tb_room_movie as rm\n" +
            "INNER JOIN tb_movie as m\n" +
            "on rm.movie_id=m.id\n" +
            "\n" +
            "INNER JOIN tb_schedule as s\n" +
            "on rm.schedule_id=s.\"id\"\n" +
            "\n" +
            "INNER JOIN tb_room as r\n" +
            "on rm.room_id=r.id\n" +
            "\n" +
            "INNER JOIN tb_cinema as c\n" +
            "on r.cinema_id=c.\"id\"\n" +
            "\n" +
            "where rm.movie_id=:movieId\n" +
            "and s.start_day=:startDay",nativeQuery = true)
    List<RoomMovie> findRoomMovieByMovieId(Long movieId, LocalDate startDay);
}

