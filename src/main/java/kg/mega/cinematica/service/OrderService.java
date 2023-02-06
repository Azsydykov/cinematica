package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.responces.Response;

import java.util.List;

public interface OrderService extends BaseService<OrderDto>{
    OrderDto book(Long roomMovieId,List<Long> seatScheduleList);
    OrderDto create();

}
