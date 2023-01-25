package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.RoomDto;
import kg.mega.cinematica.models.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto>{
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
}
