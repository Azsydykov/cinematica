package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMoviePriceMapper extends BaseMapper<RoomMoviePrice, RoomMoviePriceDto>{
    RoomMoviePriceMapper INSTANCE = Mappers.getMapper(RoomMoviePriceMapper.class);
}
