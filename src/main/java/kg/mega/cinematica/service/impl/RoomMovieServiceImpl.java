package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.RoomMovieRep;
import kg.mega.cinematica.exceptions.RoomMovieNotFoundException;
import kg.mega.cinematica.mappers.RoomMovieMapper;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.service.RoomMovieService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomMovieServiceImpl implements RoomMovieService {
    RoomMovieMapper mapper = RoomMovieMapper.INSTANCE;

    private final RoomMovieRep rep;

    public RoomMovieServiceImpl(RoomMovieRep rep) {
        this.rep = rep;
    }

    @Override
    public RoomMovieDto save(RoomMovieDto roomMovieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMovieDto)));
    }

    @Override
    public RoomMovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RoomMovieNotFoundException("RoomMovie not found!")));
    }

    @Override
    public RoomMovieDto delete(Long id) {
        RoomMovieDto roomMovieDto = findById(id);
        roomMovieDto.setActive(false);
        return save(roomMovieDto);
    }

    @Override
    public List<RoomMovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
