package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.SeatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatScheduleRep extends JpaRepository<SeatSchedule,Long> {
    @Query(value = "select * from tb_seat_schedule as ss" +
            "\n" +
            " INNER JOIN tb_room_movie as rm" +
            "\ton ss.room_movie_id=rm.id" +
            "\t\n" +
            "\tINNER JOIN tb_seat as s" +
            "\ton ss.seat_id=s.id" +
            "\n" +
            "\tWHERE rm.id=:roomMovieId",nativeQuery = true)
    List<SeatSchedule> findByRoomMovieId(Long roomMovieId);

    @Query(value = "SELECT id from tb_seat_schedule\n" +
            "WHERE room_movie_id=:roomMovieId and seat_id=:seatsId",nativeQuery = true)
    List<SeatSchedule> findByRoomMovieAndSeatsId(Long roomMovieId, Long seatsId);
}

//ss.id,seat_status,row,number