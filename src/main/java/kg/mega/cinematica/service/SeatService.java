package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.request.SaveSeatRequest;
import kg.mega.cinematica.models.responces.Responce;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    SeatDto create(SaveSeatRequest seat);
}
