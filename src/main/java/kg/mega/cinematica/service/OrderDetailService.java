package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.Response;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{

    OrderDetailDto create(OrderDto order, SeatScheduleDto seatSchedule);

//    List<OrderDetailResponse> create(Long seanceId, Map<Long, PriceType> priceTypeMap);
}
