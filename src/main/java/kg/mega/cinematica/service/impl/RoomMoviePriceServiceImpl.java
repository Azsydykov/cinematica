package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomMoviePriceRep;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.exceptions.RoomMoviePriceNotFoundException;
import kg.mega.cinematica.mappers.RoomMoviePriceMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import kg.mega.cinematica.models.responces.*;
import kg.mega.cinematica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;
    private final CinemaService cinemaService;
    private PriceType priceType;
    private RoomService roomService;

    @Autowired
    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, RoomMovieService roomMovieService,
                                     PriceService priceService, CinemaService cinemaService,
                                     RoomService roomService) {
        this.rep = rep;
        this.roomMovieService = roomMovieService;
        this.priceService = priceService;
        this.cinemaService = cinemaService;
        this.roomService = roomService;

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
    public List<RoomMoviePriceDto> findPriceByMovieId(Long movieId, LocalDate startDate) {
        return mapper.toDtos(rep.findPriceByMovieId(movieId, startDate));
    }

    @Override
    public GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate) {
        List<RoomMoviePriceDto> roomMoviePriceList = findPriceByMovieId(movieId, startDate);
        Collections.sort(roomMoviePriceList);

        List<RoomMovieDto> roomMovieDtos = roomMovieService.findRoomMovieByMovieId(movieId, startDate);

        List<RoomResponse> roomResponses = new ArrayList<>();

        for (RoomMovieDto roomMovieItem : roomMovieDtos) {

            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomId(roomMovieItem.getRoom().getId());
            roomResponse.setName(roomMovieItem.getRoom().getName());


            List<RoomMovieResponse> newList = new ArrayList<>();

            RoomMovieResponse roomMovieResp = new RoomMovieResponse();
            ;

            for (RoomMoviePriceDto item : roomMoviePriceList) {


                if (roomMovieItem.getSchedule().getId().equals(item.getRoomMovie().getSchedule().getId())) {
                    if (item.getRoomMovie().getId().equals(roomMovieItem.getId())) {
                        //   roomMovieResp.setId(item.getRoomMovie().getSchedule().getId());
                        //проверка на время сеанса
                        if (!newList.isEmpty() &&
                                newList.get((int) newList.stream().count() - 1).getId() == item.getRoomMovie().getId()) {
                            roomMovieResp = newList.get((int) newList.stream().count() - 1);
                            //проверка есть ли в списке уже этот зал с ценой
                            //если да, то обновляем его прайс, если нет то создаем новый


                            newList.remove((int) newList.stream().count() - 1);//удаляем чтобы не было дубликатов
                        } else {
//                            roomMovieResp = new RoomMovieResponse();
                            roomMovieResp.setId(item.getRoomMovie().getId());


                        }
                        priceType = item.getPrice().getPriceType();

                        switch (priceType) {
                            case CHILD:
                                roomMovieResp.setChildPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                            case STANDARD:
                                roomMovieResp.setStandardPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                        }

                    }
                }
            }
            newList.add(roomMovieResp);
//проверка на дубли сенансов
            for (RoomResponse rp : roomResponses) {
                if (rp.getRoomId().equals(roomResponse.getRoomId())) {
                    newList.addAll(rp.getRoomMovie());
                    roomResponses.remove(rp);
                    break;
                }

            }
            //
            roomResponse.setRoomMovie(newList);
            roomResponses.add(roomResponse);
        }

        List<CinemaResponse> cinemaResponses = new ArrayList<>();
        boolean inList = false;
        for (RoomMovieDto item : roomMovieDtos) {

            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());
            List<RoomResponse> newRoomResp = new ArrayList();

            for (RoomResponse roomResponseItem : roomResponses) {
                if (item.getRoom().getId().equals(roomResponseItem.getRoomId())) {
                    for (RoomResponse rp : newRoomResp)
                    {
                        if (rp.getRoomId().equals(item.getRoom().getId())) {
                            List<RoomMovieResponse> list = rp.getRoomMovie();
                            list.addAll(roomResponseItem.getRoomMovie());
                            newRoomResp.remove(rp);


                            roomResponseItem.setRoomMovie(list);
                            break;
                        }


                    }
                    newRoomResp.add(roomResponseItem);

                }
            }

//проверка на дубли кинотеатров
            for (CinemaResponse rp : cinemaResponses) {
                if (rp.getName().equals(cinemaResponse.getName())) {
                    inList = true;
                    newRoomResp.addAll(rp.getRooms());
                    cinemaResponses.remove(rp);
                    break;
                }

            }
            Set<RoomResponse> hashSet = new HashSet<RoomResponse>(newRoomResp);
            newRoomResp.clear();
            newRoomResp.addAll(hashSet);

            cinemaResponse.setRooms(newRoomResp);

            cinemaResponses.add(cinemaResponse);

        }


        GetRoomMovieResponse getRoomMovieResponse = new GetRoomMovieResponse();
        for (RoomMoviePriceDto item : roomMoviePriceList) {
            getRoomMovieResponse.setCinema(cinemaResponses);
            getRoomMovieResponse.setMovieName(item.getRoomMovie().getMovie().getName());
            getRoomMovieResponse.setMoviePg(item.getRoomMovie().getMovie().getPg());
            getRoomMovieResponse.setMovieImage(item.getRoomMovie().getMovie().getImage());
            getRoomMovieResponse.setDefinition(item.getRoomMovie().getMovie().getDefinition());
            getRoomMovieResponse.setMovieRating(item.getRoomMovie().getMovie().getRating());
        }
        return getRoomMovieResponse;
    }
}
