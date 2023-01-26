package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.ScheduleDto;

import java.time.LocalTime;

public interface ScheduleService extends BaseService<ScheduleDto>{
    ScheduleDto create(LocalTime startDate);
}
