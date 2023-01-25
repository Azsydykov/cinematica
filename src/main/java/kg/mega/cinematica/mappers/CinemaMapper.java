package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.entities.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CinemaMapper extends BaseMapper<Cinema, CinemaDto> {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);
}
