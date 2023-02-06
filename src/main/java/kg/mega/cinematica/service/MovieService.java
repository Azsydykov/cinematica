package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;
import kg.mega.cinematica.models.responces.GetAllMoviesResponse;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    List<MovieDto> findAllMovies(int limit, int offset);
    List<GetAllMoviesResponse> getAllMovies(int limit, int offset);
}
