package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.SeatDto;
import kg.mega.cinematica.models.request.SaveSeatRequest;
import kg.mega.cinematica.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Место")
@RestController
@RequestMapping("/api/v1/seat")
public class SeatController {

    @Autowired
    private SeatService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody SeatDto seatDto) {
            return new ResponseEntity<>(service.save(seatDto), HttpStatus.CREATED);

    }

    @PostMapping("/create")
    @ApiOperation("Создать")
    ResponseEntity<?> create(@ModelAttribute SaveSeatRequest seat) {
            return new ResponseEntity<>(service.create(seat), HttpStatus.CREATED);

    }

    @GetMapping("/findById")
    @ApiOperation("Поиск места по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
        //      return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }
    @GetMapping("/findAll")
    @ApiOperation("Вывод всех мест")
    ResponseEntity<List<SeatDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
            return ResponseEntity.ok(service.delete(id));
    }

}
