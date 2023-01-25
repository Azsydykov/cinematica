package kg.mega.cinematica.models.dto;

import kg.mega.cinematica.models.entities.Cinema;
import kg.mega.cinematica.models.entities.WorkDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.ManyToOne;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto extends WorkDateDto{
    Long id;
    CinemaDto cinema;
    int seatCount;
    boolean active;
}
