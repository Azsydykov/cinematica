package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.request.SaveOrderDetailRequest;
import kg.mega.cinematica.models.request.SaveOrderRequest;
import kg.mega.cinematica.models.responces.Responce;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{
    Responce create(Long orderId, List<Long> seatScheduleList );
    Responce create1(SaveOrderRequest order);
}
