package kg.mega.cinematica.dao;

import kg.mega.cinematica.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRep extends JpaRepository<Schedule,Long> {
}
