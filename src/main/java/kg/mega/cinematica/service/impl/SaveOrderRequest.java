package kg.mega.cinematica.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveOrderRequest {

    Long movieId;
    Long roomMovieId;

}
