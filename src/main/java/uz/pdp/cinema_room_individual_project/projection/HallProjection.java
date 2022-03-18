package uz.pdp.cinema_room_individual_project.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface HallProjection {
    String getId();
    String getName();
    @Value("#{@sessionTimeRepository.getTimesByHallId(target.id)}")
    List<SessionTimeProjection> getTimes();
}
