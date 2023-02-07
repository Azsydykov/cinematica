package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.exceptions.OrderDetailNotFoundException;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;
    private final OrderDetailRep rep;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRep rep) {
        this.rep = rep;
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDetailDto)));
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new OrderDetailNotFoundException("Order detail not found!")));
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
