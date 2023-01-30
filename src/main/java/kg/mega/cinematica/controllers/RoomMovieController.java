package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.RoomMovieDto;
import kg.mega.cinematica.models.request.SaveRoomMovieRequest;
import kg.mega.cinematica.models.responces.GetRoomMovieResponse;
import kg.mega.cinematica.service.RoomMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "ЗалФильм")
@RestController
@RequestMapping("/api/v1/roomMovie")
public class RoomMovieController {

    @Autowired
    private RoomMovieService service;


    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody RoomMovieDto roomMovieDto) {
            return new ResponseEntity<>(service.save(roomMovieDto), HttpStatus.CREATED);

    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@ModelAttribute SaveRoomMovieRequest roomMovie, @RequestParam List<Long> priceId) {
        return new ResponseEntity<>(service.create(roomMovie,priceId), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех")
    ResponseEntity<List<RoomMovieDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


    @GetMapping("/getRoomMovieByMovieId")
    @ApiOperation("Вывод сеанса")
    ResponseEntity<List<GetRoomMovieResponse>> getRoomMovieByMovieId(Long movieId, @DateTimeFormat(pattern = "MM-dd") LocalDateTime startDate) {
        return ResponseEntity.ok(service.getRoomMovieByMovieId(movieId, startDate));
    }

}
