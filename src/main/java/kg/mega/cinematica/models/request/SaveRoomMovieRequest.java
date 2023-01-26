package kg.mega.cinematica.models.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveRoomMovieRequest {
    Long movieId;
   // Long priceId;
    Long roomId;
    Long scheduleId;
}
