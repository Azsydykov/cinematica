package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.request.SaveOrderRequest;
import kg.mega.cinematica.models.responces.Response;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{
    Response create(Long orderId, List<Long> seatScheduleList );
    Response create1(SaveOrderRequest order);
}
