package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.entities.RoomMovie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMovieMapper extends BaseMapper<RoomMovie, RoomMovieDto>{
    RoomMovieMapper INSTANCE = Mappers.getMapper(RoomMovieMapper.class);

}
