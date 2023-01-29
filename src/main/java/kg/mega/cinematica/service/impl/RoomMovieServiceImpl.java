package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomMovieRep;
import kg.mega.cinematica.exceptions.RoomMovieNotFoundException;
import kg.mega.cinematica.mappers.RoomMovieMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.request.SaveRoomMovieRequest;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomMovieServiceImpl implements RoomMovieService {
    RoomMovieMapper mapper = RoomMovieMapper.INSTANCE;

    private final RoomMovieRep rep;
    private final MovieService movieService;
    private final PriceService priceService;
    private final RoomService roomService;
    private final ScheduleService scheduleService;

    public RoomMovieServiceImpl(RoomMovieRep rep, MovieService movieService,
                                PriceService priceService, RoomService roomService,
                                ScheduleService scheduleService) {
        this.rep = rep;
        this.movieService = movieService;
        this.priceService = priceService;
        this.roomService = roomService;
        this.scheduleService = scheduleService;
    }

    @Override
    public RoomMovieDto save(RoomMovieDto roomMovieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMovieDto)));
    }

    @Override
    public Responce create(SaveRoomMovieRequest roomMovie, List<Long> priceDtoList) {
        MovieDto movieDto = movieService.findById(roomMovie.getMovieId());
        RoomDto roomDto = roomService.findById(roomMovie.getRoomId());
        ScheduleDto scheduleDto = scheduleService.findById(roomMovie.getScheduleId());
        RoomMovieDto roomMovieDto = new RoomMovieDto();
        for (Long id : priceDtoList) {
            PriceDto priceDto = priceService.findById(id);

            roomMovieDto.setMovie(movieDto);
            roomMovieDto.setRoom(roomDto);
            roomMovieDto.setSchedule(scheduleDto);
            roomMovieDto.setPrice(priceDto);
            save(roomMovieDto);
        }
        return new Responce("Success!");
    }

    @Override
    public RoomMovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomMovieNotFoundException("RoomMovie not found!")));
    }

    @Override
    public RoomMovieDto delete(Long id) {
        RoomMovieDto roomMovieDto = findById(id);
        roomMovieDto.setActive(false);
        return save(roomMovieDto);
    }

    @Override
    public List<RoomMovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
