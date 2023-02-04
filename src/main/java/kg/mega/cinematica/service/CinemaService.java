package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.entities.Cinema;
import kg.mega.cinematica.models.request.SaveCinemaRequest;

import java.util.List;

public interface CinemaService extends BaseService<CinemaDto>{
    CinemaDto create(SaveCinemaRequest cinema);
    List<CinemaDto> findCinemaByRoomId(Long roomId);
}
