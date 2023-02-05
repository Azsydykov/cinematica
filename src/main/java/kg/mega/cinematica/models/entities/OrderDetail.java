package kg.mega.cinematica.models.entities;

import kg.mega.cinematica.enums.PriceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_order_detail")
public class OrderDetail extends WorkDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    SeatSchedule seatSchedule;
    @ManyToOne
    Order order;
    @Enumerated(EnumType.STRING)
    PriceType priceType;






}
