package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomMoviePriceRep;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.exceptions.RoomMoviePriceNotFoundException;
import kg.mega.cinematica.mappers.RoomMoviePriceMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.responces.*;
import kg.mega.cinematica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;
    private final CinemaService cinemaService;
    private PriceType priceType;

    @Autowired
    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, RoomMovieService roomMovieService,
                                     PriceService priceService, CinemaService cinemaService) {
        this.rep = rep;
        this.roomMovieService = roomMovieService;
        this.priceService = priceService;
        this.cinemaService = cinemaService;

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
    public List<RoomMoviePriceDto> findPriceByMovieId(Long roomMovieId) {

        return mapper.toDtos(rep.findPriceByMovieId(roomMovieId));
    }


    @Override
    public GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate) {
        List<RoomMoviePriceDto> roomMoviePriceList = findPriceByMovieId(movieId);
        Collections.sort(roomMoviePriceList);
//new
        List<RoomMovieResponse> roomMovieResponseList = new ArrayList<>();


        for (RoomMoviePriceDto item : roomMoviePriceList) {
            RoomMovieResponse roomMovieResponse;

            if (!roomMovieResponseList.isEmpty() &&
                    roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1).getId() == item.getRoomMovie().getId()) {
                roomMovieResponse = roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1);
                 //проверка есть ли в списке уже этот зал с ценой
                //если да, то обновляем его прайс, если нет то создаем новый


                roomMovieResponseList.remove((int) roomMovieResponseList.stream().count() - 1);//удаляем чтобы не было дубликатов
            } else {
                roomMovieResponse = new RoomMovieResponse();
                roomMovieResponse.setId(item.getRoomMovie().getId());


            }
            priceType = item.getPrice().getPriceType();


            switch (priceType) {
                case CHILD:
                    roomMovieResponse.setChildPrice(item.getPrice().getPrice());
                    roomMovieResponse.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                    break;
                case STUDENT:
                    roomMovieResponse.setStudentPrice(item.getPrice().getPrice());
                    roomMovieResponse.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                    break;
                case ADULTS:
                    roomMovieResponse.setStandartPrice(item.getPrice().getPrice());
                    roomMovieResponse.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                    break;
            }
            boolean inList1 = false;
            for (RoomMovieResponse rmr : roomMovieResponseList) {
                if (rmr.getStartTime().equals(roomMovieResponse.getStartTime())) {
                    inList1 = true;
                    break;
                }
            }
            {
                if (!inList1)
                    roomMovieResponseList.add(roomMovieResponse);
            }
        }
        List<RoomMovieDto> roomMovieList = roomMovieService.findRoomMovieByMovieId(movieId, startDate);
        List<RoomResponse> roomResponseList = new ArrayList<>();
        List<CinemaResponse> cinemaResponseList = new ArrayList<>();


        for (RoomMovieDto item : roomMovieList) {

            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setName(item.getRoom().getName());


            roomResponse.setRoomMovie(roomMovieResponseList);
            boolean inList = false;
            for (RoomResponse rp : roomResponseList) {
                if (rp.getName().equals(roomResponse.getName())) {
                    inList = true;
                    break;
                }

            }
            if (!inList)
                roomResponseList.add(roomResponse);

            inList = false;
            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());
            cinemaResponse.setRooms(roomResponseList);
            for (CinemaResponse rp : cinemaResponseList) {
                if (rp.getName().equals(cinemaResponse.getName())) {
                    inList = true;
                    break;
                }

            }
            if (!inList)
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
