package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.entities.OrderDetail;
import kg.mega.cinematica.models.responces.OrderDetailResponse;
import kg.mega.cinematica.models.responces.OrderResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{
    List<OrderDetailResponse> getOrderDetailRes(Long orderDetailId);
    List<OrderDetailDto> findByOrderId(Long orderId);
}
