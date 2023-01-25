package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.CinemaRep;
import kg.mega.cinematica.mappers.CinemaMapper;
import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.request.SaveCinemaRequest;
import kg.mega.cinematica.service.CinemaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    CinemaMapper mapper = CinemaMapper.INSTANCE;

    private final CinemaRep rep;

    public CinemaServiceImpl(CinemaRep rep) {
        this.rep = rep;
    }

    @Override
    public CinemaDto save(CinemaDto cinemaDto) {
        return mapper.toDto(rep.save(mapper.toEntity(cinemaDto)));
    }


    @Override
    public CinemaDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found!")));
    }

    @Override
    public CinemaDto delete(Long id) {
        CinemaDto cinemaDto = findById(id);
        cinemaDto.setActive(false);
        return save(cinemaDto);
    }

    @Override
    public List<CinemaDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

}
