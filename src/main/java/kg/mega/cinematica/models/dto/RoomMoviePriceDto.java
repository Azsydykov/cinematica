package kg.mega.cinematica.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMoviePriceDto extends WorkDateDto implements Comparable<RoomMoviePriceDto> {

    Long id;
    RoomMovieDto roomMovie;
    PriceDto price;


    @Override
    public int compareTo(RoomMoviePriceDto o) {
        return roomMovie.getId().compareTo(o.roomMovie.getId());
    }

}
