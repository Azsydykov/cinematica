package kg.mega.cinematica.models.dto;

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

    Long id;
    SeatScheduleDto schedule;
    OrderDto order;
    boolean active;
}
