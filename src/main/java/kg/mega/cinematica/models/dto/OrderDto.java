package kg.mega.cinematica.models.dto;
//
//import kg.mega.cinematica.models.entities.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto extends WorkDateDto {

    Long id;
    Double price;
    LocalDateTime orderDate;

}
