package kg.mega.cinematica.models.responces;

import kg.mega.cinematica.models.dto.SeatDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    int row;
    int seatNumber;



}
