package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;

    private final OrderDetailRep rep;

    public OrderDetailServiceImpl(OrderDetailRep rep) {
        this.rep = rep;
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDetailDto)));
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Order detail not found!")));
    }

    @Override
    public OrderDetailDto delete(Long id) {
        OrderDetailDto orderDetailDto = findById(id);
        orderDetailDto.setActive(false);
        return save(orderDetailDto);
    }

    @Override
    public List<OrderDetailDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
