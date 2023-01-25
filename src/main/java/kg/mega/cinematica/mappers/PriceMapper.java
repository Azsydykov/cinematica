package kg.mega.cinematica.mappers;

import kg.mega.cinematica.models.dto.PriceDto;
import kg.mega.cinematica.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto>{
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
}
