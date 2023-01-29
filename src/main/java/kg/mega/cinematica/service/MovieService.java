package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;

import java.util.Map;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    Map<String,String> getAllMovie();
}
