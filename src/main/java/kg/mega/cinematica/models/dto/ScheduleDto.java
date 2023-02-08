package kg.mega.cinematica.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto extends WorkDateDto {

    Long id;
    LocalDate startDay;
    LocalTime startTime;


}
