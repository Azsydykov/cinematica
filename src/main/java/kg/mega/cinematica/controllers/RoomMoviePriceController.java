package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.RoomMoviePriceDto;
import kg.mega.cinematica.models.responses.*;
import kg.mega.cinematica.service.RoomMoviePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "Цена сеанса")
@RestController
@RequestMapping("/api/v1/roomMoviePrice")
public class RoomMoviePriceController {

    @Autowired
    private RoomMoviePriceService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody RoomMoviePriceDto roomMoviePriceDto) {
            return new ResponseEntity<>(service.save(roomMoviePriceDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@RequestParam Long roomMovieId, @RequestParam  List<Long> priceId) {

            return new ResponseEntity<>(service.create(roomMovieId,priceId), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех")
    ResponseEntity<List<RoomMoviePriceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
            return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getRoomMovieByMovieId")
    @ApiOperation("Вывод сеанса по id фильма")
    ResponseEntity<GetRoomMovieResponse> getRoomMovieByMovieId(@RequestParam Long movieId,
                                                               @RequestParam
                                                               @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay) {
            return ResponseEntity.ok(service.getRoomMovieByMovieId(movieId, startDay));
        //(defaultValue = "yyyy-MM-dd")
    }

    @GetMapping("/getRoomMovieByCinemaId")
    @ApiOperation("Вывод сеанса по id кинотеатра")
    ResponseEntity<GetRoomMovieByCinemaResponse> getRoomMovieByCinemaId(@RequestParam Long cinemaId,
                                                                       @RequestParam
                                                               @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay) {
        return ResponseEntity.ok(service.getRoomMovieByCinemaId(cinemaId, startDay));
    }

    @GetMapping("/getRoomMovieResponse")
    @ApiOperation("Вывод сеанса по id кинотеатра test1")
    ResponseEntity<List<RoomMovieResponse>> getRoomMovieResponse(@RequestParam Long cinemaId,
                                                                 @RequestParam
                                                                        @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay) {
        return ResponseEntity.ok(service.getRoomMovieResponse(cinemaId, startDay));
    }

    @GetMapping("/getMovieResponse")
    @ApiOperation("Вывод сеанса по id кинотеатра test2")
    ResponseEntity<List<MovieResponse>> getMovieResponse(@RequestParam Long cinemaId,
                                                         @RequestParam
                                                                       @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay) {
        return ResponseEntity.ok(service.getMovieResponse(cinemaId, startDay));
    }

    @GetMapping("/getRoomResp")
    @ApiOperation("Вывод сеанса по id кинотеатра test3")
    ResponseEntity<List<RoomResp>> getRoomResp(@RequestParam Long cinemaId,
                                               @RequestParam
                                                         @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay) {
        return ResponseEntity.ok(service.getRoomResp(cinemaId, startDay));
    }
}
