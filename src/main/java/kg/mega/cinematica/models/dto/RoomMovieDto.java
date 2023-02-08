package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.cinematica.models.entities.RoomMoviePrice;
import kg.mega.cinematica.models.entities.WorkDate;
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
