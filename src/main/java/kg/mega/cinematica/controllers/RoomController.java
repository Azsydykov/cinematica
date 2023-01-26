package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.RoomDto;
import kg.mega.cinematica.models.request.SaveRoomRequest;
import kg.mega.cinematica.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Зал")
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody RoomDto roomDto) {

            return new ResponseEntity<>(service.save(roomDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создать")
    ResponseEntity<?> create(@ModelAttribute SaveRoomRequest room) {

            return new ResponseEntity<>(service.create(room), HttpStatus.CREATED);

    }

    @GetMapping("/findById")
    @ApiOperation("Поиск зала по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех залов")
    ResponseEntity<List<RoomDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {

        return ResponseEntity.ok(service.delete(id));

    }
}
