package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.ScheduleDto;
import kg.mega.cinematica.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule, ScheduleDto>{
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
}
