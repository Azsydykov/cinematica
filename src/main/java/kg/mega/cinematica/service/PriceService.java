package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.request.SavePriceRequest;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(SavePriceRequest price);
}
