package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaDto extends WorkDateDto{
   // @JsonIgnore
    Long id;
    String name;
    String address;
    String logo;






}
