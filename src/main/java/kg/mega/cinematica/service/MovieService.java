package kg.mega.cinematica.service;

import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);
}
