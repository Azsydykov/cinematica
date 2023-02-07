package kg.mega.cinematica.models.responces;

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
public class OrderResponse {
    String movieName;
    String cinemaName;
    String room;
    List<SeatResponse> seats;
    LocalDate startDay;
    LocalTime startTime;
    double totalPrice;


}
