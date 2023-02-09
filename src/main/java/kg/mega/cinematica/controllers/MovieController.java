package kg.mega.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;
import kg.mega.cinematica.models.responces.GetAllMoviesResponse;
import kg.mega.cinematica.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Фильм")
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(service.save(movieDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@ModelAttribute SaveMovieRequest movie) {
        return new ResponseEntity<>(service.create(movie), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск фильма по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех фильмов")
    ResponseEntity<List<MovieDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getAllMovies")
    @ApiOperation("Вывод всех фильмов (limit/offset)")
    ResponseEntity<List<GetAllMoviesResponse>> getAllMovies(@RequestParam int limit, @RequestParam int offset) {
        return ResponseEntity.ok(service.getAllMovies(limit, offset));
    }

}
