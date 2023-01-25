package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.OrderDto;
import kg.mega.cinematica.models.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


}
