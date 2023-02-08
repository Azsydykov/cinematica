package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.models.entities.Price;
import kg.mega.cinematica.models.entities.RoomMovie;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.ManyToOne;

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
