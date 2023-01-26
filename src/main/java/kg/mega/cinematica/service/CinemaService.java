package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.request.SaveCinemaRequest;

public interface CinemaService extends BaseService<CinemaDto>{
    CinemaDto create(SaveCinemaRequest cinema);

}
