package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    Long id;
    LocalDate startDay;
    LocalTime startTime;


}
