package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.ScheduleDto;
import kg.mega.cinematica.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Api(tags = "Расписание")
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody ScheduleDto scheduleDto) {
            return new ResponseEntity<>(service.save(scheduleDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@RequestParam(defaultValue = "dd-MM-yyyy")
                             @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDay,
                             @RequestParam(defaultValue = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm") LocalTime startTime) {
            return new ResponseEntity<>(service.create(startDay,startTime), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск расписания по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех расписаний")
    ResponseEntity<List<ScheduleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
