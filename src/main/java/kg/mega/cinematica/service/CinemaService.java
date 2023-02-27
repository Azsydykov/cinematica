package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.request.SaveCinemaRequest;
import kg.mega.cinematica.models.responses.GetAllCinemasResponse;

import java.util.List;

public interface CinemaService extends BaseService<CinemaDto>{
    CinemaDto create(SaveCinemaRequest cinema);

    List<CinemaDto> findAllCinemas(int limit, int offset);
    List<GetAllCinemasResponse> getAllCinemas(int limit, int offset);
}
