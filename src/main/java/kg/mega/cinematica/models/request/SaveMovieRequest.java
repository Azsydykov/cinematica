package kg.mega.cinematica.models.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveMovieRequest {

    String definition;
    String name;
    Double rating;
    String pg;
    String image;
}
