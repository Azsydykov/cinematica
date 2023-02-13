package kg.mega.cinematica.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMovieDto extends WorkDateDto {

    Long id;
    RoomDto room;
    MovieDto movie;
    ScheduleDto schedule;
    List<RoomMoviePriceDto> roomMoviePrice;



}
