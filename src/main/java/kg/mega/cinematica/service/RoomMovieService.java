package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.request.SaveRoomMovieRequest;
import kg.mega.cinematica.models.responces.Response;

import java.time.LocalDate;
import java.util.List;

public interface RoomMovieService extends BaseService<RoomMovieDto>{
    RoomMovieDto create(SaveRoomMovieRequest roomMovie);

    List<RoomMovieDto> getAllByMovieId(Long id);

    List<RoomMovieDto> findRoomMovieByMovieId(Long movieId, LocalDate startDate);
}
