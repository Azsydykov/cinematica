package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomMoviePriceRep;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.exceptions.RoomMoviePriceNotFoundException;
import kg.mega.cinematica.mappers.RoomMoviePriceMapper;
import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.responces.*;
import kg.mega.cinematica.service.PriceService;
import kg.mega.cinematica.service.RoomMoviePriceService;
import kg.mega.cinematica.service.RoomMovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;

    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, RoomMovieService roomMovieService, PriceService priceService) {
        this.rep = rep;
        this.roomMovieService = roomMovieService;
        this.priceService = priceService;
    }

    @Override
    public RoomMoviePriceDto save(RoomMoviePriceDto roomMoviePriceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMoviePriceDto)));
    }

    @Override
    public Response create(Long roomMovieId, List<Long> priceList) {
        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);

        for (Long id : priceList) {
            PriceDto priceDto = priceService.findById(id);

            RoomMoviePriceDto roomMoviePriceDto = new RoomMoviePriceDto();
            roomMoviePriceDto.setRoomMovie(roomMovieDto);
            roomMoviePriceDto.setPrice(priceDto);
            save(roomMoviePriceDto);
        }

        return new Response("Saved Successfully");
    }

    @Override
    public RoomMoviePriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomMoviePriceNotFoundException("RoomMoviePrice not found!")));
    }

    @Override
    public RoomMoviePriceDto delete(Long id) {
        RoomMoviePriceDto roomMoviePriceDto = findById(id);
        roomMoviePriceDto.setActive(false);
        return save(roomMoviePriceDto);
    }

    @Override
    public List<RoomMoviePriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<RoomMoviePriceDto> findPriceByRoomMovieId(Long roomMovieId) {

        return mapper.toDtos(rep.findPriceByRoomMovieId(roomMovieId));
    }


    @Override
    public GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate) {
        List<RoomMoviePriceDto> roomMoviePriceList = findPriceByRoomMovieId(movieId);


        List<RoomMovieResponse> roomMovieResponseList = new ArrayList<>();
        for (RoomMoviePriceDto item : roomMoviePriceList) {

            RoomMovieResponse roomMovieResponse = new RoomMovieResponse();
            roomMovieResponse.setId(item.getRoomMovie().getId());

            //????????????????????????????????????????????????????????????
            roomMovieResponse.setChildPrice(item.getPrice().getPrice());
            roomMovieResponse.setStudentPrice(item.getPrice().getPrice());
            roomMovieResponse.setStandartPrice(item.getPrice().getPrice());


            roomMovieResponse.setStartTime(item.getRoomMovie().getSchedule().getStartTime()); //проверить как выведит

            roomMovieResponseList.add(roomMovieResponse);
        }

        List<RoomMovieDto> roomMovieList = roomMovieService.findRoomMovieByMovieId(movieId, startDate);
        List<RoomResponse> roomResponseList = new ArrayList<>();



        for (RoomMovieDto item : roomMovieList) {
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setName(item.getRoom().getName());
            roomResponse.setRoomMovie(roomMovieResponseList);
            roomResponseList.add(roomResponse);
        }

        List<CinemaResponse> cinemaResponseList = new ArrayList<>();

        for (RoomMovieDto item : roomMovieList) {
            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());
            cinemaResponse.setRooms(roomResponseList);
            cinemaResponseList.add(cinemaResponse);

        }

        GetRoomMovieResponse getRoomMovieResponse = new GetRoomMovieResponse();
        for (RoomMoviePriceDto item : roomMoviePriceList) {
            getRoomMovieResponse.setCinema(cinemaResponseList);
            getRoomMovieResponse.setMovieName(item.getRoomMovie().getMovie().getName());
            getRoomMovieResponse.setMoviePg(item.getRoomMovie().getMovie().getPg());
            getRoomMovieResponse.setMovieImage(item.getRoomMovie().getMovie().getImage());
            getRoomMovieResponse.setDefinition(item.getRoomMovie().getMovie().getDefinition());
            getRoomMovieResponse.setMovieRating(item.getRoomMovie().getMovie().getRating());

        }
        return getRoomMovieResponse;
    }
}
