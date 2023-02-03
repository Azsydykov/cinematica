package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.exceptions.OrderDetailNotFoundException;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;
    private final OrderDetailRep rep;
//    private final SeatScheduleService seatScheduleService;


    public OrderDetailServiceImpl(OrderDetailRep rep
                                  ) {
        this.rep = rep;
//        this.seatScheduleService = seatScheduleService;
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
    public Response create(List<Long> seatScheduleList) {
        OrderDto orderDto = new OrderDto();
        orderDto.setPrice(null);


//        for (Long id : seatScheduleList) {
//            SeatScheduleDto seatScheduleDto = seatScheduleService.findById(id);
//            OrderDetailDto orderDetailDto = new OrderDetailDto();
//            orderDetailDto.setOrder(orderDto);
//            orderDetailDto.setSeatSchedule(seatScheduleDto);
//            save(orderDetailDto);
//        }



        return new Response("Success");
    }
}
