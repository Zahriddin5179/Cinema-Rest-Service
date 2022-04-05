package uz.pdp.cinema_room_individual_project.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface HallProjection {
    String getHallId();
    String getHallName();
    @Value("#{@sessionTimeRepository.getTimesByHallId(target.hallId)}")
    List<SessionTimeProjection> getTimes();
}
