package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.exceptions.OrderDetailNotFoundException;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.responces.OrderDetailResponse;
import kg.mega.cinematica.service.OrderDetailService;
import kg.mega.cinematica.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;
    private final OrderDetailRep rep;
    private final PriceService priceService;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRep rep, PriceService priceService) {
        this.rep = rep;
        this.priceService = priceService;
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
    public List<OrderDetailDto> findByOrderId(Long orderId) {
        return mapper.toDtos(rep.findByOrderId(orderId));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailRes(Long orderId) {
        List<OrderDetailDto> orderDetailDtoList = findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();

        for (OrderDetailDto item : orderDetailDtoList) {
            OrderDetailResponse  orderDetailResponse = new OrderDetailResponse();

            orderDetailResponse.setOrderId(orderDetailDtoList.get(0).getOrder().getId());
            orderDetailResponse.setMovie(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getMovie().getName());
            orderDetailResponse.setCinema(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getRoom().getCinema().getName());
            orderDetailResponse.setRoom(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getRoom().getName());
            orderDetailResponse.setStartDay(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getSchedule().getStartDay());
            orderDetailResponse.setStartTime(orderDetailDtoList.get(0).getSeatSchedule().getRoomMovie().getSchedule().getStartTime());

            orderDetailResponse.setRow(item.getSeatSchedule().getSeat().getRow());
            orderDetailResponse.setSeatNumber(item.getSeatSchedule().getSeat().getNumber());
            orderDetailResponse.setPriceType(item.getPriceType());
            double price = priceService.getPrice(item.getPriceType());
            orderDetailResponse.setPrice(price);
            orderDetailResponseList.add(orderDetailResponse);

        }
        return orderDetailResponseList;
    }
}
