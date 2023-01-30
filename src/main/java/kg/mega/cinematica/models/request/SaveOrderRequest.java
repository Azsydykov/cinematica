package kg.mega.cinematica.models.request;

import kg.mega.cinematica.models.dto.SeatDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveOrderRequest {
    Long movieId;
//    Long cinemaId;
    Long roomId;
    Long scheduleId;
    Long priceId;
    int seatNum;
    int seatRow;



}

