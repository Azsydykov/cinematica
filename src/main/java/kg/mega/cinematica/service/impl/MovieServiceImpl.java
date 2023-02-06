package kg.mega.cinematica.service.impl;

import kg.mega.cinematica.dao.MovieRep;
import kg.mega.cinematica.exceptions.MovieNotFoundException;
import kg.mega.cinematica.mappers.MovieMapper;
import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;
import kg.mega.cinematica.models.responces.GetAllMoviesResponse;
import kg.mega.cinematica.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    MovieMapper mapper = MovieMapper.INSTANCE;

    private final MovieRep rep;
    @Autowired
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
    public List<MovieDto> findAllMovies(int limit, int offset) {
        return mapper.toDtos(rep.findAllMovies(limit,offset));
    }

    @Override
    public List<GetAllMoviesResponse> getAllMovies(int limit, int offset) {
        List<MovieDto> movieList = findAllMovies(limit,offset);
        List<GetAllMoviesResponse> getAllMoviesResponseList = new ArrayList<>();
        GetAllMoviesResponse getAllMoviesResponse= new GetAllMoviesResponse();
        for (MovieDto item:movieList){
            getAllMoviesResponse.setId(item.getId());
            getAllMoviesResponse.setName(item.getName());
            getAllMoviesResponse.setRating(item.getRating());
            getAllMoviesResponse.setPg(item.getPg());
            getAllMoviesResponse.setImageLink(item.getImage());
            getAllMoviesResponseList.add(getAllMoviesResponse);
        }
        return getAllMoviesResponseList ;
    }
}
