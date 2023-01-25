package kg.mega.cinematica.models.entities;

import kg.mega.cinematica.enums.SeatStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_seat_schedule")
public class SeatSchedule extends WorkDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Seat seat;
    @ManyToOne
    RoomMovie roomMovie;
    @Enumerated(EnumType.STRING)
    SeatStatus seatStatus;
    boolean active;



    @PrePersist
    protected void onCreate() {
        seatStatus = SeatStatus.FREE;
    }







}
