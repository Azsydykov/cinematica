package kg.mega.cinematica.models.dto;
//
//import kg.mega.cinematica.models.entities.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.cinematica.models.entities.WorkDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto extends WorkDateDto {
    @JsonIgnore
    Long id;
    Double price;
    LocalDateTime startDate;

}
