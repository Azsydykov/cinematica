package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.models.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail, OrderDetailDto>{
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

}
