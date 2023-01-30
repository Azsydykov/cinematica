package kg.mega.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRoomMovieResponse {

    String movieName;
    String movieImage;
    String definition;
    String moviePg;
    String movieRating;
    List<CinemaResponse> cinema;

}
