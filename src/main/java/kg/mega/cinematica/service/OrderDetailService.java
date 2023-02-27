package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.responses.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{
    List<OrderDetailResponse> getOrderDetailRes(Long orderDetailId);
    List<OrderDetailDto> findByOrderId(Long orderId);
}
