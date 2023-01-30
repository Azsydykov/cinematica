package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    List<String> getAllMovie();

    List<String> getAllMovies(int limit, int offset);
}
