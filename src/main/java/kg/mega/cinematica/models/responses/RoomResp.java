package kg.mega.cinematica.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResp {
    Long roomId;
    String name;
    List<MovieResponse> movieResponse;
}
