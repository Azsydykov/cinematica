package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.SeatScheduleRep;
import kg.mega.cinematica.enums.SeatStatus;
import kg.mega.cinematica.exceptions.SeatScheduleNotFoundException;
import kg.mega.cinematica.mappers.SeatScheduleMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.models.responces.RoomResponse;
import kg.mega.cinematica.models.responces.SeatScheduleResponse;
import kg.mega.cinematica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMovieService roomMovieService;
    private final RoomService roomService;

    @Autowired
    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMovieService roomMovieService,
                                   RoomService roomService) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
        this.roomService = roomService;
    }


    @Override
    public SeatScheduleDto save(SeatScheduleDto seatScheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatScheduleDto)));
    }

    @Override
    public SeatScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new SeatScheduleNotFoundException("Seat Schedule not found!")));
    }

    @Override
    public SeatScheduleDto delete(Long id) {
        SeatScheduleDto seatScheduleDto = findById(id);
        seatScheduleDto.setActive(false);
        return save(seatScheduleDto);
    }

    @Override
    public List<SeatScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }


    @Override
    public List<SeatScheduleDto> findByRoomMovieId(Long roomMovieId) {
        return mapper.toDtos(rep.findByRoomMovieId(roomMovieId));
    }

    @Override
    public List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId) {
        List<SeatScheduleDto> seatScheduleList = findByRoomMovieId(roomMovieId);

        List<SeatScheduleResponse> seatScheduleResList = new ArrayList<>();

        RoomDto roomDto = roomService.findRoomByRoomMovieId(roomMovieId);

        List<SeatDto> seatList = seatService.findSeatsByRoomId(roomDto.getId());

        for (SeatDto seatsItem : seatList) {
            SeatScheduleResponse seatScheduleResponse = new SeatScheduleResponse();

            if (seatScheduleList.isEmpty()) {
                seatScheduleResponse.setSeatScheduleId(seatsItem.getId());
                seatScheduleResponse.setStatus(SeatStatus.FREE);
                seatScheduleResponse.setSeatNum(seatsItem.getNumber());
                seatScheduleResponse.setRow(seatsItem.getRow());
            }
            for (SeatScheduleDto seatScheduleItem : seatScheduleList) {
                SeatScheduleDto seatScheduleDto = seatScheduleItem;

                if (seatsItem.getId().equals(seatScheduleDto.getSeat().getId())) {
                    seatScheduleResponse.setSeatScheduleId(seatScheduleDto.getSeat().getId());
                    seatScheduleResponse.setStatus(seatScheduleItem.getSeatStatus());
                    seatScheduleResponse.setRow(seatScheduleItem.getSeat().getRow());
                    seatScheduleResponse.setSeatNum(seatScheduleItem.getSeat().getNumber());
                    break;
                } else {
                    seatScheduleResponse.setSeatScheduleId(seatsItem.getId());
                    seatScheduleResponse.setStatus(SeatStatus.FREE);
                    seatScheduleResponse.setSeatNum(seatsItem.getNumber());
                    seatScheduleResponse.setRow(seatsItem.getRow());
                }
            }
            seatScheduleResList.add(seatScheduleResponse);
        }
        return seatScheduleResList;
    }

    @Override
    public List<SeatScheduleDto> findByRoomMovieAndSeatsId(Long roomMovieId,Long seatId) {
        return mapper.toDtos(rep.findByRoomMovieAndSeatsId(roomMovieId,seatId));
    }

    @Override
    public SeatScheduleDto create(Long roomMovieId, List<Long> seatsId) {
        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);

        SeatScheduleDto seatScheduleDto = new SeatScheduleDto();
        for (Long id : seatsId) {
            SeatDto seatDto = seatService.findById(id);

            seatScheduleDto.setRoomMovie(roomMovieDto);
            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setSeatStatus(SeatStatus.BOUGHT);
            save(seatScheduleDto);
        }
        return seatScheduleDto ;
    }
}
