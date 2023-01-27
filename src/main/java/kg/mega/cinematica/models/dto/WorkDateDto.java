package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public  class WorkDateDto {
    @JsonIgnore
    LocalDateTime addDate;
    @JsonIgnore
    LocalDateTime updateDate;
    @JsonIgnore
    boolean active;

}
