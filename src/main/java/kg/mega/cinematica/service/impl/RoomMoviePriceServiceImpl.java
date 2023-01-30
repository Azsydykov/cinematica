package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.PriceRep;
import kg.mega.cinematica.dao.RoomMoviePriceRep;
import kg.mega.cinematica.exceptions.PriceNotFoundException;
import kg.mega.cinematica.mappers.PriceMapper;
import kg.mega.cinematica.mappers.RoomMoviePriceMapper;
import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.request.SavePriceRequest;
import kg.mega.cinematica.models.request.SaveRoomMoviePriceRequest;
import kg.mega.cinematica.models.request.SaveRoomMovieRequest;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.service.PriceService;
import kg.mega.cinematica.service.RoomMoviePriceService;
import kg.mega.cinematica.service.RoomMovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;


    private final RoomMoviePriceRep rep;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;

    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep,RoomMovieService roomMovieService,PriceService priceService) {
        this.rep = rep;
        this.roomMovieService=roomMovieService;
        this.priceService=priceService;
    }

    @Override
    public RoomMoviePriceDto save(RoomMoviePriceDto roomMoviePriceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMoviePriceDto)));
    }

    @Override
    public Responce create(Long roomMovieId, List<Long> priceList ) {
        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);

        for (Long id: priceList){
            PriceDto priceDto = priceService.findById(id);

            RoomMoviePriceDto roomMoviePriceDto = new RoomMoviePriceDto();
            roomMoviePriceDto.setRoomMovie(roomMovieDto);
            roomMoviePriceDto.setPrice(priceDto);
            save(roomMoviePriceDto);
        }

        return new Responce("Saved Successfully");
    }

    @Override
    public RoomMoviePriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new PriceNotFoundException("RoomMoviePrice not found!")));
    }

    @Override
    public RoomMoviePriceDto delete(Long id) {
        RoomMoviePriceDto roomMoviePriceDto = findById(id);
        roomMoviePriceDto.setActive(false);
        return save(roomMoviePriceDto);
    }

    @Override
    public List<RoomMoviePriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
