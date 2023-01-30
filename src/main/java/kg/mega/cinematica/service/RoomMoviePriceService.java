package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.request.SaveRoomMoviePriceRequest;
import kg.mega.cinematica.models.responces.Responce;

import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{

    Responce create(Long roomMovieId, List<Long> priceList);
}
