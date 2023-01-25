package kg.mega.cinematica.models.dto;

import kg.mega.cinematica.models.entities.WorkDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto extends WorkDateDto {
    Long id;
    LocalTime startDate;
    boolean active;
}
