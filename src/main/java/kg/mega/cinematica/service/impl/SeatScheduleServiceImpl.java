package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.SeatScheduleRep;
import kg.mega.cinematica.mappers.SeatScheduleMapper;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.service.SeatScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;

    public SeatScheduleServiceImpl(SeatScheduleRep rep) {
        this.rep = rep;
    }

    @Override
    public SeatScheduleDto save(SeatScheduleDto seatScheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatScheduleDto)));
    }

    @Override
    public SeatScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Seat Schedule not found!")));
    }

    @Override
    public SeatScheduleDto delete(Long id) {
        SeatScheduleDto seatScheduleDto = findById(id);
        seatScheduleDto.setActive(false);
        return save(seatScheduleDto);
    }

    @Override
    public List<SeatScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
