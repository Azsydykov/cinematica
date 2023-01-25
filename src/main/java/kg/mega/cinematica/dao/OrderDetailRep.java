package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRep extends JpaRepository<OrderDetail, Long> {
}
