package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.request.SaveRoomMovieRequest;
import kg.mega.cinematica.models.responces.Responce;

import java.util.List;

public interface RoomMovieService extends BaseService<RoomMovieDto>{
    Responce create(SaveRoomMovieRequest roomMovie, List<Long> prices);

    List<RoomMovieDto> getAllByMovieId(Long id);

    List<String> getRoomMovieByMovieId(Long movieId);
}
