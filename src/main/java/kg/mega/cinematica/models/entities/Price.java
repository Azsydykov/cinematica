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
@Table(name = "tb_price")
public class Price extends WorkDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    @Enumerated(EnumType.STRING)
    PriceType priceType;
    boolean active;

    @PrePersist
    protected void onCreate() {
        active = true;
    }





}
