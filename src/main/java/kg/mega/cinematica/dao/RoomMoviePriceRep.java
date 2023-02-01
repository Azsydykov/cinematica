package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.RoomMoviePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomMoviePriceRep extends JpaRepository<RoomMoviePrice,Long> {

    @Query(value = "\tSELECT * FROM tb_room_movie_price as rmp INNER JOIN" +
            "\ttb_room_movie as rm on rmp.room_movie_id=rm.id" +
            "\twhere rm.movie_id=:roomMovieId",nativeQuery = true)
    List<RoomMoviePrice> findPriceByRoomMovieId(Long roomMovieId);


}
