package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.SeatRep;
import kg.mega.cinematica.exceptions.SeatNotFoundException;
import kg.mega.cinematica.mappers.SeatMapper;
import kg.mega.cinematica.models.dto.RoomDto;
import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.request.SaveSeatRequest;
import kg.mega.cinematica.service.RoomService;
import kg.mega.cinematica.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    SeatMapper mapper = SeatMapper.INSTANCE;

    private final SeatRep rep;
    private final RoomService roomService;

    public SeatServiceImpl(SeatRep rep, RoomService roomService) {
        this.rep = rep;
        this.roomService = roomService;
    }

    @Override
    public SeatDto save(SeatDto seatDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatDto)));
    }

    @Override
    public SeatDto book(SaveSeatRequest seat) {
        RoomDto roomDto = roomService.findById(seat.getRoomId());
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        seatDto.setRow(seat.getRow());
        seatDto.setRoom(roomDto);
        return save(seatDto);
    }

    @Override
    public SeatDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new SeatNotFoundException("Seat nor found!")));
    }

    @Override
    public SeatDto delete(Long id) {
        SeatDto seatDto = findById(id);
        seatDto.setActive(false);
        return save(seatDto);
    }

    @Override
    public List<SeatDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
