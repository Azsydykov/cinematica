package kg.mega.cinematica.service;

import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.responses.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService extends BaseService<OrderDto>{
    OrderResponse book(Long roomMovieId, Map<Long,PriceType> seatListAndPriceType);
    OrderDto create();
    OrderResponse getOrderDetail(List<OrderDetailDto> orderDetailDtoList);




}
