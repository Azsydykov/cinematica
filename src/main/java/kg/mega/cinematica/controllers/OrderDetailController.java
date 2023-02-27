package kg.mega.cinematica.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.OrderDetailDto;
import kg.mega.cinematica.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Детали заказа")
@RestController
@RequestMapping("/api/v1/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody OrderDetailDto orderDetailDto) {
            return new ResponseEntity<>(service.save(orderDetailDto), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск деталей заказа по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех деталей заказа")
    ResponseEntity<List<OrderDetailDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
            return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/findByOrderId")
    @ApiOperation("Поиск деталей заказа по id заказа")
    ResponseEntity<?> findByOrderId(@RequestParam Long id) {

        return  ResponseEntity.ok(service.getOrderDetailRes(id));
    }

}
