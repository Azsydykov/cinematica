package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderRep;
import kg.mega.cinematica.exceptions.OrderNotFoundException;
import kg.mega.cinematica.mappers.OrderMapper;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;
    private final OrderRep rep;
    private final OrderDetailService orderDetailService;
    private final RoomMoviePriceService roomMoviePriceService;
    private final SeatScheduleService seatScheduleService;

    @Autowired
    public OrderServiceImpl(OrderRep rep,
                            OrderDetailService orderDetailService,
                            RoomMoviePriceService roomMoviePriceService,
                            SeatScheduleService seatScheduleService) {
        this.rep = rep;
        this.orderDetailService = orderDetailService;
        this.roomMoviePriceService = roomMoviePriceService;
        this.seatScheduleService = seatScheduleService;
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
    public Response create(List<Long> seatScheduleList) {
        OrderDto orderDto = new OrderDto();

        for (Long id : seatScheduleList) {
            SeatScheduleDto seatScheduleDto = seatScheduleService.findById(id);
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrder(orderDto);
            orderDetailDto.setSeatSchedule(seatScheduleDto);
            orderDetailService.save(orderDetailDto);

        }

        return null;
    }
}
