package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RoomMovieRep extends JpaRepository<RoomMovie,Long> {


    @Query(value = "SELECT * from tb_room_movie as rm WHERE rm.movie_id=:movieId",nativeQuery = true)
    List<RoomMovie> getAllByMovieId(Long movieId);

    @Query(value = "SELECT rm.* from tb_room_movie as rm\n" +
            "\tinner JOIN tb_schedule as s on\n" +
            "\trm.\"id\"=s.\"id\"\n" +
            "\tWHERE rm.movie_id=:movieId and s.start_day=:startDay",nativeQuery = true)
    List<RoomMovie> findRoomMovieByMovieId(Long movieId, LocalDate startDay);
}

