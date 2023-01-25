package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.SeatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatScheduleRep extends JpaRepository<SeatSchedule,Long> {
}
