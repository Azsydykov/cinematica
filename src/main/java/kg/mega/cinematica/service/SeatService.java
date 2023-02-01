package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.request.SaveSeatRequest;

public interface SeatService extends BaseService<SeatDto>{
    SeatDto create(SaveSeatRequest seat);
}
