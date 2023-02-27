package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.CinemaRep;
import kg.mega.cinematica.exceptions.CinemaNotFoundException;
import kg.mega.cinematica.mappers.CinemaMapper;
import kg.mega.cinematica.models.dto.CinemaDto;
import kg.mega.cinematica.models.request.SaveCinemaRequest;
import kg.mega.cinematica.models.responses.GetAllCinemasResponse;
import kg.mega.cinematica.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    CinemaMapper mapper = CinemaMapper.INSTANCE;


    private final CinemaRep rep;
    @Autowired
    public CinemaServiceImpl(CinemaRep rep) {
        this.rep = rep;
    }

    @Override
    public CinemaDto save(CinemaDto cinemaDto) {
        return mapper.toDto(rep.save(mapper.toEntity(cinemaDto)));
    }


    @Override
    public CinemaDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new CinemaNotFoundException("Cinema not found!")));
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

    @Override
    public CinemaDto create(SaveCinemaRequest cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setName(cinema.getName());
        cinemaDto.setAddress(cinema.getAddress());
        cinemaDto.setLogo(cinema.getLogo());
        return save(cinemaDto);
    }

    @Override
    public List<CinemaDto> findAllCinemas(int limit, int offset) {
        return mapper.toDtos(rep.findAllCinemas(limit,offset));
    }

    @Override
    public List<GetAllCinemasResponse> getAllCinemas(int limit, int offset) {
        List<CinemaDto> cinemasList = findAllCinemas(limit,offset);
        List<GetAllCinemasResponse> getAllCinemasResponseList = new ArrayList<>();

        for (CinemaDto item:cinemasList){
            GetAllCinemasResponse getAllCinemasResponse= new GetAllCinemasResponse();
            getAllCinemasResponse.setId(item.getId());
            getAllCinemasResponse.setName(item.getName());
            getAllCinemasResponse.setLogo(item.getLogo());
            getAllCinemasResponse.setAddress(item.getAddress());
            getAllCinemasResponseList.add(getAllCinemasResponse);
        }
        return getAllCinemasResponseList;
    }
}
