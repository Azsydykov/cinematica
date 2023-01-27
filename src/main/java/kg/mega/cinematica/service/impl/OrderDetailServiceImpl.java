package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.exceptions.OrderDetailNotFoundException;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.service.OrderDetailService;
import kg.mega.cinematica.service.OrderService;
import kg.mega.cinematica.service.SeatScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;
    private final OrderDetailRep rep;
    private final OrderService orderService;
    private final SeatScheduleService seatScheduleService;

    public OrderDetailServiceImpl(OrderDetailRep rep, OrderService orderService, SeatScheduleService seatScheduleService) {
        this.rep = rep;
        this.orderService = orderService;
        this.seatScheduleService = seatScheduleService;
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDetailDto)));
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new OrderDetailNotFoundException("Order detail not found!")));
    }

    @Override
    public OrderDetailDto delete(Long id) {
        OrderDetailDto orderDetailDto = findById(id);
        orderDetailDto.setActive(false);
        return save(orderDetailDto);
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public Responce create(Long orderId, List<Long> seatScheduleList) {

        OrderDto orderDto = orderService.findById(orderId);

        for (Long id : seatScheduleList) {
            SeatScheduleDto seatScheduleDto = seatScheduleService.findById(id);
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrder(orderDto);
            orderDetailDto.setSeatSchedule(seatScheduleDto);
            save(orderDetailDto);
        }
        return new Responce("Success");
    }
}
