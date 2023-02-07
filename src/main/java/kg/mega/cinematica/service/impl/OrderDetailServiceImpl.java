package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.OrderDetailRep;
import kg.mega.cinematica.exceptions.OrderDetailNotFoundException;
import kg.mega.cinematica.mappers.OrderDetailMapper;
import kg.mega.cinematica.models.dto.*;
import kg.mega.cinematica.models.responces.Response;
import kg.mega.cinematica.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(propagation = Propagation.REQUIRED)
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

    @Override
    public OrderDetailDto create(OrderDto order, SeatScheduleDto seatSchedule) {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setOrder(order);
        orderDetailDto.setSeatSchedule(seatSchedule);

        return save(orderDetailDto);
    }

//    @Override
//    public List<OrderDetailResponse> create(Long seanceId, Map<Long, PriceType> priceTypeMap) {
//
//        List  <RoomMoviePriceDto> roomMoviePriceDtos = roomMoviePriceService.findByRoomMovieId(seanceId);
//        List<Long> seatScheduleId=new ArrayList<>();
//        for (Map.Entry<Long, PriceType> entry : priceTypeMap.entrySet())  {
//            SeatScheduleDto seatScheduleDto = new SeatScheduleDto();
//
//            SeatDto seatDto = seatService.findById(entry.getKey());
//
//            RoomMoviePriceDto roomMoviePriceDto=roomMoviePriceService.findByPriceType(seanceId,entry.getValue());
//            if (entry.getValue()==PriceType.CHILD){
//                seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
//            }
//
//            else{
//                seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
//            }
//
//
////            seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
//            seatScheduleDto.setSeat(seatDto);
//            seatScheduleDto.setSeatStatus(SeatStatus.SOLD);
//
//            seatScheduleDto=save(seatScheduleDto);
//            seatScheduleId.add(seatScheduleDto.getId());
//        }
//
//
//
//        OrderDto orderDto=orderService.create();
//
//        List<OrderDetailDto>list=new ArrayList<>();
//
//        List<OrderDetailResponse>responseList=new ArrayList<>();
//
//        for(Long item:seatScheduleId){
//
//            SeatScheduleDto seatScheduleDto=findById(item);
//            OrderDetailDto orderDetailDto=new OrderDetailDto();
//            orderDetailDto.setSeatSchedule(seatScheduleDto);
//            orderDetailDto.setOrder(orderDto);
//            orderDetailService.save(orderDetailDto);
//            list.add(orderDetailDto);
//        }
//
//        for(OrderDetailDto item:list){
//            OrderDetailResponse response=new OrderDetailResponse();
//            response.setOrderNumber(item.getOrder().getId());
//            response.setCinema(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getCinema().getName());
//            response.setRoom(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getName());
//            response.setMovie(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getMovie().getName());
//            response.setStarTime(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getSchedule().getStartTime());
//            response.setRow(item.getSeatSchedule().getSeat().getRow());
//            response.setNumber(item.getSeatSchedule().getSeat().getNumber());
//            response.setCost(item.getSeatSchedule().getRoomMoviePrice().getPrice().getPrice());
//            responseList.add(response);
//        }
//
//        return responseList;
//
//    }
}
