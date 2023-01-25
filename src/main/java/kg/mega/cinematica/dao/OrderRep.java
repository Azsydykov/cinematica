package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRep extends JpaRepository<Order, Long> {
}
