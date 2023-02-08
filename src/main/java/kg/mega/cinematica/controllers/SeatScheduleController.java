package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.exceptions.ExceptionHandler;
import kg.mega.cinematica.exceptions.RoomMovieNotFoundException;
import kg.mega.cinematica.models.dto.SeatScheduleDto;
import kg.mega.cinematica.models.responces.SeatScheduleResponse;
import kg.mega.cinematica.service.SeatScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Расписание мест")
@RestController
@RequestMapping("/api/v1/seatSchedule")
public class SeatScheduleController {

    @Autowired
    private SeatScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody SeatScheduleDto seatScheduleDto) {
        return new ResponseEntity<>(service.save(seatScheduleDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@RequestParam Long roomMovieId,
                             @RequestParam List<Long> seatsId) {
        return new ResponseEntity<>(service.create(roomMovieId, seatsId), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск seatSchedule по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех seatSchedule")
    ResponseEntity<List<SeatScheduleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getdByRoomMovieId")
    @ApiOperation("Поиск по id сеанса")
    ResponseEntity<List<SeatScheduleResponse>> getByRoomMovieId(@RequestParam Long roomMovieId) {
        try {
            return ResponseEntity.ok(service.getByRoomMovieId(roomMovieId));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
