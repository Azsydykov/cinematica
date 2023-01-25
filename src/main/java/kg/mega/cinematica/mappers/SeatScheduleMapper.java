package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.entities.SeatSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SeatScheduleMapper extends BaseMapper<SeatSchedule, SeatScheduleDto> {
    SeatScheduleMapper INSTANCE = Mappers.getMapper(SeatScheduleMapper.class);
}
