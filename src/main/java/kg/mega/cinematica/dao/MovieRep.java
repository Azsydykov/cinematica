package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieRep extends JpaRepository<Movie,Long> {

//    @Query("SELECT start_date from tb_room_movie rm INNER JOIN tb_schedule s on rm.schedule_id=s.\"id\"" +
//            " INNER JOIN tb_movie m on rm.movie_id=m.\"id\" where m.\"id=:id")
//    List<Movie> getScheduleByMovieId(Long id);
}
