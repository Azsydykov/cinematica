package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderRep;
import kg.mega.cinematica.exceptions.OrderNotFoundException;
import kg.mega.cinematica.mappers.OrderMapper;
import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;
    private final OrderRep rep;
    private final MovieService movieService;
    private final PriceService priceService;
    private final RoomService roomService;
    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final RoomMovieService roomMovieService;

    public OrderServiceImpl(OrderRep rep, MovieService movieService, PriceService priceService,
                            RoomService roomService, ScheduleService scheduleService,
                            SeatService seatService, RoomMovieService roomMovieService) {
        this.rep = rep;
        this.movieService = movieService;
        this.priceService = priceService;
        this.roomService = roomService;
        this.scheduleService = scheduleService;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {





        return mapper.toDto(rep.save(mapper.toEntity(orderDto)));
    }


    @Override
    public OrderDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found!")));
    }

    @Override
    public OrderDto delete(Long id) {
        OrderDto orderDto = findById(id);
        orderDto.setActive(false);
        return save(orderDto);
    }

    @Override
    public List<OrderDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

}
