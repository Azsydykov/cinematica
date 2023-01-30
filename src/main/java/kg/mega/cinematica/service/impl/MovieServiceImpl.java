package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.MovieRep;
import kg.mega.cinematica.exceptions.MovieNotFoundException;
import kg.mega.cinematica.mappers.MovieMapper;
import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.entities.Movie;
import kg.mega.cinematica.models.request.SaveMovieRequest;
import kg.mega.cinematica.models.responces.GetAllMovieResponce;
import kg.mega.cinematica.models.responces.Responce;
import kg.mega.cinematica.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    MovieMapper mapper = MovieMapper.INSTANCE;

    private final MovieRep rep;

    public MovieServiceImpl(MovieRep rep) {
        this.rep = rep;
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(movieDto)));
    }

    @Override
    public MovieDto create(SaveMovieRequest movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setDefinition(movie.getDefinition());
        movieDto.setRating(movie.getRating());
        movieDto.setPg(movie.getPg());
        movieDto.setImage(movie.getImage());

        return save(movieDto);
    }

    @Override
    public MovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found!")));
    }

    @Override
    public MovieDto delete(Long id) {
        MovieDto movieDto = findById(id);
        movieDto.setActive(false);
        return save(movieDto);
    }


    @Override
    public List<MovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<String> getAllMovie() {
        List<MovieDto> listMovie = findAll();
        List<String> movieList = new ArrayList<>();

        for (MovieDto item : listMovie) {
            movieList.add(item.getId() + " " +item.getName()+ " " +item.getImage()+ " " +item.getPg());
        }
        return movieList;
    }

    @Override
    public List<String> getAllMovies(int limit, int offset) {
        List<MovieDto> movieList = mapper.toDtos(rep.getAllMovies(limit,offset));
        List<String> allMovieList = new ArrayList<>();
        for (MovieDto item:movieList){
            allMovieList.add("ID="+item.getId()+", "+item.getName()+", "+item.getImage()+ ", " +item.getPg());
        }


        return allMovieList;
    }
}
