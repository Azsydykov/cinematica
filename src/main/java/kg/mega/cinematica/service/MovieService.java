package kg.mega.cinematica.service;

import kg.mega.cinematica.dao.MovieRep;
import kg.mega.cinematica.models.dto.MovieDto;
import kg.mega.cinematica.models.request.SaveMovieRequest;
import kg.mega.cinematica.models.responces.GetAllMovieResponce;
import kg.mega.cinematica.models.responces.Responce;

import java.util.List;
import java.util.Map;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    List<String> getAllMovie();

    List<String> getAllMovies(int limit, int offset);
}
