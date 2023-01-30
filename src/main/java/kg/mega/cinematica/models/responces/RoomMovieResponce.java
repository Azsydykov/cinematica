package kg.mega.cinematica.models.responces;

import kg.mega.cinematica.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMovieResponce {
    Long id;
    PriceType priceType;
    Double price;
    LocalDateTime startDate;

}
