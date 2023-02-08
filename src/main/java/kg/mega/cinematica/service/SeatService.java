package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.request.SaveSeatRequest;
import kg.mega.cinematica.models.responces.Response;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    Response create(SaveSeatRequest seat);

    List<SeatDto> findSeatsByRoomId(Long roomMovieId);
}
