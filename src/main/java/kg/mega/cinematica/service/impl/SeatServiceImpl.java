package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.SeatRep;
import kg.mega.cinematica.exceptions.SeatNotFoundException;
import kg.mega.cinematica.mappers.SeatMapper;
import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    SeatMapper mapper = SeatMapper.INSTANCE;

    private final SeatRep rep;

    public SeatServiceImpl(SeatRep rep) {
        this.rep = rep;
    }

    @Override
    public SeatDto save(SeatDto seatDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatDto)));
    }

    @Override
    public SeatDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new SeatNotFoundException("Seat nor found!")));
    }

    @Override
    public SeatDto delete(Long id) {
        SeatDto seatDto = findById(id);
        seatDto.setActive(false);
        return save(seatDto);
    }

    @Override
    public List<SeatDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
