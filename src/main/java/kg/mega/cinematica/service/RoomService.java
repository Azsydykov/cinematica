package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.RoomDto;
import kg.mega.cinematica.models.request.SaveRoomRequest;

public interface RoomService extends BaseService<RoomDto>{
    RoomDto create(SaveRoomRequest room);
    RoomDto findRoomByRoomMovieId(Long roomMovieId);

}
