package kg.mega.cinematica.dao;

import kg.mega.cinematica.enums.PriceType;
import kg.mega.cinematica.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRep extends JpaRepository<Price,Long> {

    @Query("Select p.price from Price as p\n" +
            "WHERE p.priceType=:priceType")
    List<Price> findPrice(PriceType priceType);
}
