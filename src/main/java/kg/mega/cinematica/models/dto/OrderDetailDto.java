package kg.mega.cinematica.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.models.entities.Order;
import kg.mega.cinematica.models.entities.OrderDetail;
import kg.mega.cinematica.models.entities.SeatSchedule;
import kg.mega.cinematica.models.entities.WorkDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.ManyToOne;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDto extends WorkDateDto {
    @JsonIgnore
    Long id;
    SeatScheduleDto seatSchedule;
    OrderDto order;
    PriceType priceType;

}
