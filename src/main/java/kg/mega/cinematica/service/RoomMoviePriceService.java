package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import kg.mega.cinematica.models.request.SaveRoomMoviePriceRequest;
import kg.mega.cinematica.models.responces.GetRoomMovieResponse;
import kg.mega.cinematica.models.responces.Response;

import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{

    Response create(Long roomMovieId, List<Long> priceList);
    List<RoomMoviePriceDto> findPriceByMovieId(Long movieId,LocalDate startDate);
    GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate);
    List<RoomMoviePriceDto> findRoomMoviePriceByRoomMovieId(Long roomMovieId);

}
