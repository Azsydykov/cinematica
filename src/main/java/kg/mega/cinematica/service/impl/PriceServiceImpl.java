package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.PriceRep;
import kg.mega.cinematica.exceptions.PriceNotFoundException;
import kg.mega.cinematica.mappers.PriceMapper;
import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.request.SavePriceRequest;
import kg.mega.cinematica.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {

    PriceMapper mapper = PriceMapper.INSTANCE;

    private final PriceRep rep;

    public PriceServiceImpl(PriceRep rep) {
        this.rep = rep;
    }

    @Override
    public PriceDto save(PriceDto priceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(priceDto)));
    }

    @Override
    public PriceDto create(SavePriceRequest price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setPriceType(price.getPriceType());

        return save(priceDto);
    }

    @Override
    public PriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new PriceNotFoundException("Price not found!")));
    }

    @Override
    public PriceDto delete(Long id) {
        PriceDto priceDto = findById(id);
        priceDto.setActive(false);
        return save(priceDto);
    }

    @Override
    public List<PriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
