package kg.mega.cinematica.models.responces;

import kg.mega.cinematica.enums.SeatStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleResponce {
    long seatScheduleId;
    SeatStatus status;
    int row;
    int seatNum;
}
