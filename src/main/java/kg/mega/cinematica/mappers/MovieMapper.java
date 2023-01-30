package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovieMapper extends BaseMapper<Movie, MovieDto> {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);


}
