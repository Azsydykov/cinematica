package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.responces.GetRoomMovieResponse;
import kg.mega.cinematica.models.responces.Response;

import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{

    Response create(Long roomMovieId, List<Long> priceList);
    List<RoomMoviePriceDto> findPriceByMovieId(Long roomMovieId);

    GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate);

}
