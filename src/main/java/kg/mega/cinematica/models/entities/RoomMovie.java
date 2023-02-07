package kg.mega.cinematica.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_room_movie")
public class RoomMovie extends WorkDate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Room room;
    @ManyToOne
    Movie movie;
    @ManyToOne
    Schedule schedule;
//    @OneToMany(mappedBy = "roomMovie")
//    List<RoomMoviePrice> roomMoviePrice;






}
