package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.models.responces.SeatScheduleResponse;

import java.util.List;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    Response create(Long roomMovieId, List<Long> seatIds);

   List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId);
    List<SeatScheduleDto> findByRoomMovieId(Long roomMovieId);
}
