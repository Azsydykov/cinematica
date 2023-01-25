package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRep extends JpaRepository<Price,Long> {
}
