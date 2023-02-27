package kg.mega.cinematica.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    Long movieId;
    String name;
    List<RoomMovieResponse> roomMovie;

    @Override
    public int hashCode()
    {
        return Long.valueOf(movieId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
            final MovieResponse other = (MovieResponse) obj;
        if (this.movieId != other.movieId) {
            return false;
        }
        return true;
    }
}
