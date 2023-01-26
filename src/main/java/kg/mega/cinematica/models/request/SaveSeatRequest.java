package kg.mega.cinematica.models.request;

import kg.mega.cinematica.models.dto.RoomDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveSeatRequest {

    Long roomId;
    int number;
    int row;
}
