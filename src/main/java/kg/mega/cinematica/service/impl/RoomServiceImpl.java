package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomRep;
import kg.mega.cinematica.exceptions.RoomNotFoundException;
import kg.mega.cinematica.mappers.RoomMapper;
import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.dto.RoomDto;
import kg.mega.cinematica.models.request.SaveRoomRequest;
import kg.mega.cinematica.service.CinemaService;
import kg.mega.cinematica.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    RoomMapper mapper = RoomMapper.INSTANCE;
    private final RoomRep rep;
    private final CinemaService cinemaService;


    @Autowired
    public RoomServiceImpl(RoomRep rep, CinemaService cinemaService) {
        this.rep = rep;
        this.cinemaService = cinemaService;
    }

    @Override
    public RoomDto save(RoomDto roomDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomDto)));
    }

    @Override
    public RoomDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found!")));
    }

    @Override
    public RoomDto delete(Long id) {
        RoomDto roomDto = findById(id);
        roomDto.setActive(false);
        return save(roomDto);
    }

    @Override
    public List<RoomDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomDto create(SaveRoomRequest room) {
        CinemaDto cinema = cinemaService.findById(room.getCinemaId());
        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setSeatCount(room.getSeatCount());
        roomDto.setCinema(cinema);
        return save(roomDto);

    }

    @Override
    public RoomDto findRoomByRoomMovieId(Long roomMovieId) {
        return mapper.toDto(rep.findRoomByRoomMovieId(roomMovieId));
    }
}
