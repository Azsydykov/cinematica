package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.models.responces.SeatScheduleResponce;

import java.util.List;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    Responce create(Long roomMovieId, List<Long> seatIds);

   List<SeatScheduleResponce> findByRoomMovieId(Long roomMovieId);
}
