package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ScheduleService extends BaseService<ScheduleDto>{
    ScheduleDto create(LocalDateTime stardDate);

}
