package kg.mega.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetAllMovieResponce {
    int limit;
    int offset;
}
