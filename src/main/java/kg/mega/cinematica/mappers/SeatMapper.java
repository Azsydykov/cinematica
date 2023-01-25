package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SeatMapper extends BaseMapper<Seat, SeatDto>{
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);

}
