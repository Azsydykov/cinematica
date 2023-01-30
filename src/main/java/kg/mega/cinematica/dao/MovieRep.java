package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRep extends JpaRepository<Movie,Long> {

    @Query(value = "select * from tb_movie LIMIT :limit OFFSET :offset",nativeQuery = true)
    List<Movie> getAllMovies(int limit, int offset);


}
