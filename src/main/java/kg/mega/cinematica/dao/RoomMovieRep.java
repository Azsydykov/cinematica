package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomMovieRep extends JpaRepository<RoomMovie,Long> {


    //    @Query("select DISTINCT c.name as cinema,r.name as room,s.start_date, m.name as movie from tb_room_movie as rm
    //
    // INNER JOIN tb_movie as m
    //	on rm.movie_id=m."id"
    //
    //	INNER JOIN tb_room as r
    //	on rm.room_id=r.id
    //
    //	INNER JOIN tb_cinema as c
    //	on r.cinema_id=c."id"
    //
    //	INNER JOIN tb_schedule as s
    //	on rm.schedule_id=s."id"
    //
    //
    //	WHERE m.id=:movieId")

//
//        @Query("select c.name,r.name,s.startDate, m.name from RoomMovie as rm\n" +
//            "\n" +
//            " INNER JOIN Movie as m\n" +
//            "\ton rm.movie=m.id\n" +
//            "\t\n" +
//            "\tINNER JOIN Room as r \n" +
//            "\ton rm.room=r.id\n" +
//            "\t\n" +
//            "\tINNER JOIN Cinema as c\n" +
//            "\ton r.cinema=c.id\n" +
//            "\t\n" +
//            "\tINNER JOIN Schedule as s\n" +
//            "\ton rm.schedule=s.id\n" +
//            "\n" +
//            "\t\n" +
//            "\tWHERE m.id=:movieId")

    @Query(value = "SELECT * from tb_room_movie as rm WHERE rm.movie_id=:movieId",nativeQuery = true)
    List<RoomMovie> getAllByMovieId(Long movieId);
}
