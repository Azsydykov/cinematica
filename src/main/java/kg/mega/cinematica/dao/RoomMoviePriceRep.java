package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.RoomMoviePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface RoomMoviePriceRep extends JpaRepository<RoomMoviePrice,Long> {

    @Query(value = "select * from tb_room_movie_price as rmp\n" +
            "inner join tb_room_movie as rm on rmp.room_movie_id=rm.id\n" +
            "\n" +
            "inner join tb_schedule as s on rm.schedule_id=s.id\n" +
            "where rm.movie_id=:movieId and  s.start_day=:startDate",nativeQuery = true)
    List<RoomMoviePrice> findPriceByMovieId(Long movieId,LocalDate startDate);

    @Query(value = "select * from tb_room_movie_price as rmp\n" +
            "inner join tb_room_movie as rm on rmp.room_movie_id=rm.id\n" +
            "inner join tb_schedule as s on rm.schedule_id=s.id\n" +
            "INNER JOIN tb_room as r on rm.room_id=r.\"id\"\n" +
            "INNER JOIN tb_cinema as c on c.\"id\"=r.cinema_id\n" +
            "where c.\"id\"=:cinemaId and s.start_day=:startDate",nativeQuery = true)
    List<RoomMoviePrice> findPriceByCinemaId(Long cinemaId,LocalDate startDate);
}