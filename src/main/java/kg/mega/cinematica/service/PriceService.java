package kg.mega.cinematica.service;

import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.request.SavePriceRequest;

import java.util.List;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(SavePriceRequest price);
    Double getPrice(PriceType priceType);
    List<PriceDto> findPrice(PriceType priceType);
}
