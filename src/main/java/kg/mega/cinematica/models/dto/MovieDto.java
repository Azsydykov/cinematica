package kg.mega.cinematica.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto extends WorkDateDto {

    Long id;
    String definition;
    String name;
    Double rating;
    int pg;
    boolean active;
    String image;


}
