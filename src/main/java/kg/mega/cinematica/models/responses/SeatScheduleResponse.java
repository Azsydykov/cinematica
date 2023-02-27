package kg.mega.cinematica.models.responses;

import kg.mega.cinematica.enums.SeatStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleResponse {
    long seatScheduleId;
    SeatStatus status;
    int row;
    int seatNum;
}
