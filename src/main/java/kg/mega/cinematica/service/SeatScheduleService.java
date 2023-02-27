package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responses.SeatScheduleResponse;

import java.util.List;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    SeatScheduleDto create(Long roomMovieId, List<Long> seatIds);
    List<SeatScheduleDto> findByRoomMovieAndSeatsId(Long roomMovieId, Long seatId);

   List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId);
    List<SeatScheduleDto> findByRoomMovieId(Long roomMovieId);
}
