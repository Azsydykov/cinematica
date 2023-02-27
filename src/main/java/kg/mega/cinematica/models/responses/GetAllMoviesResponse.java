package kg.mega.cinematica.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetAllMoviesResponse {

    Long id;
    String name;
    String imageLink;
    String Pg;
    Double rating;
}
