package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderRep;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.enums.SeatStatus;
import kg.mega.cinematica.exceptions.OrderNotFoundException;
import kg.mega.cinematica.mappers.OrderMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import kg.mega.cinematica.models.responces.OrderResponse;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.models.responces.SeatResponse;
import kg.mega.cinematica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;
    private final OrderRep rep;
    private final OrderDetailService orderDetailService;
    private final SeatService seatService;
    private final SeatScheduleService seatScheduleService;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;

    @Autowired
    public OrderServiceImpl(OrderRep rep,
                            OrderDetailService orderDetailService,
                            SeatScheduleService seatScheduleService,
                            SeatService seatService, RoomMovieService roomMovieService,
                            PriceService priceService) {
        this.rep = rep;
        this.orderDetailService = orderDetailService;
        this.seatScheduleService = seatScheduleService;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
        this.priceService = priceService;
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

    @Override
    public OrderDto create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setPrice(0.0);

        return save(orderDto);
    }

    @Override
    public OrderResponse book(Long roomMovieId, Map<Long, PriceType> seatIdAndPriceType) {
        OrderDto orderDto = create();

        SeatScheduleDto seatScheduleDto = new SeatScheduleDto();
        List<SeatScheduleDto> scheduleDtos;
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        List<PriceType> priceTypeList = new ArrayList<>();
        for (Map.Entry<Long, PriceType> entry : seatIdAndPriceType.entrySet()) {
            List<Long> seatsId = new ArrayList<>();
            seatsId.add(entry.getKey());
            priceTypeList.add(entry.getValue());
            SeatDto seatDto = seatService.findById(entry.getKey());
            RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);

            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setRoomMovie(roomMovieDto);
            seatScheduleDto.setSeatStatus(SeatStatus.BOUGHT);
            seatScheduleService.save(seatScheduleDto);

            scheduleDtos = seatScheduleService.findByRoomMovieAndSeatsId(roomMovieId, entry.getKey());


            for (SeatScheduleDto item : scheduleDtos) {
                SeatScheduleDto seatScheduleDto1 = seatScheduleService.findById(item.getId());
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setOrder(orderDto);
                orderDetailDto.setPriceType(entry.getValue());
                orderDetailDto.setSeatSchedule(seatScheduleDto1);
                orderDetailService.save(orderDetailDto);
                orderDetailDtoList.add(orderDetailDto);
            }
        }
        OrderResponse orderResponse = getOrderDetail(orderDetailDtoList);
        return orderResponse;
    }

    @Override
    public OrderResponse getOrderDetail(List<OrderDetailDto> orderDetailDtoList) {
        OrderDto orderDto = orderDetailDtoList.get(0).getOrder();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setMovieName(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getMovie().getName());
        orderResponse.setRoom(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getRoom().getName());
        orderResponse.setCinemaName(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getRoom().getCinema().getName());
        orderResponse.setStartDay(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getSchedule().getStartDay());
        orderResponse.setStartTime(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getSchedule().getStartTime());

        double totalPrice = 0;
        List<SeatResponse> seats = new ArrayList<>();

        for (OrderDetailDto item : orderDetailDtoList) {
            SeatResponse seatResponse = new SeatResponse();
            seatResponse.setRow(item.getSeatSchedule().getSeat().getRow());
            seatResponse.setSeatNumber(item.getSeatSchedule().getSeat().getNumber());
            seats.add(seatResponse);

            totalPrice += priceService.getPrice(item.getPriceType());

        }

        orderDto.setPrice(totalPrice);
        save(orderDto);

        orderResponse.setSeats(seats);
        orderResponse.setTotalPrice(totalPrice);
        return orderResponse;


    }
}
