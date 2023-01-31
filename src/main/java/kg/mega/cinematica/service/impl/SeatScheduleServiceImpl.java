package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.SeatScheduleRep;
import kg.mega.cinematica.enums.SeatStatus;
import kg.mega.cinematica.exceptions.SeatScheduleNotFoundException;
import kg.mega.cinematica.mappers.SeatScheduleMapper;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.models.responces.SeatScheduleResponce;
import kg.mega.cinematica.service.OrderService;
import kg.mega.cinematica.service.RoomMovieService;
import kg.mega.cinematica.service.SeatScheduleService;
import kg.mega.cinematica.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMovieService roomMovieService;
    private final OrderService orderService;

    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMovieService roomMovieService, OrderService orderService) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
        this.orderService = orderService;

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
    public Responce create(Long roomMovieId, List<Long> seatsId) {
        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);


        for (Long id : seatsId) {
            SeatDto seatDto = seatService.findById(id);
            SeatScheduleDto seatScheduleDto = new SeatScheduleDto();

            seatScheduleDto.setRoomMovie(roomMovieDto);
            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setSeatStatus(SeatStatus.BOOKED);
            save(seatScheduleDto);
        }
        return new Responce("Saved successfully");
    }

    @Override
    public List<SeatScheduleResponce> findByRoomMovieId(Long roomMovieId) {
        List<SeatScheduleDto> seatScheduleList = mapper.toDtos(rep.getByRoomMovieId(roomMovieId));

        List<SeatScheduleResponce> seatScheduleResList = new ArrayList<>();
        for (SeatScheduleDto item : seatScheduleList) {
            SeatScheduleResponce seatScheduleResponce = new SeatScheduleResponce();
            seatScheduleResponce.setSeatScheduleId(item.getId());
            seatScheduleResponce.setStatus(item.getSeatStatus());
            seatScheduleResponce.setRow(item.getSeat().getRow());
            seatScheduleResponce.setSeatNum(item.getSeat().getNumber());
            seatScheduleResList.add(seatScheduleResponce);
        }

        return seatScheduleResList;
    }
}
