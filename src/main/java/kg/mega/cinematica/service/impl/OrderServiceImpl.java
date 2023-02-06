package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderRep;
import kg.mega.cinematica.enums.PriceType;
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

    private final SeatScheduleService seatScheduleService;

    @Autowired
    public OrderServiceImpl(OrderRep rep,
                            OrderDetailService orderDetailService,
                            SeatScheduleService seatScheduleService) {
        this.rep = rep;
        this.orderDetailService = orderDetailService;
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
    public OrderDto create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setPrice(0.0);

        return save(orderDto);
    }

    @Override
    public OrderDto book(Long roomMovieId, List<Long> seatList) {
        OrderDto orderDto = create();
        seatScheduleService.create(roomMovieId, seatList);
        List<SeatScheduleDto> seatScheduleDtoList = seatScheduleService.findByRoomMovieAndSeatsId(roomMovieId);

        for (SeatScheduleDto item : seatScheduleDtoList) {

            //условие, чтобы сохранял только с теми seatID которые приходят

            for (Long seatItem : seatList) {
                if(item.getSeat().getId().equals(seatItem)){
                SeatScheduleDto seatScheduleDto = seatScheduleService.findById(item.getId());
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setOrder(orderDto);
                orderDetailDto.setPriceType(PriceType.CHILD);
                orderDetailDto.setSeatSchedule(seatScheduleDto);
                orderDetailService.save(orderDetailDto);
            }else {
                    break;
                }
        }
        }
        return orderDto;
    }


}
