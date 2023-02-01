package kg.mega.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMovieResponse {
    Long id;
    Double childPrice;
    Double standartPrice;
    Double studentPrice;
    LocalTime startTime;

}
