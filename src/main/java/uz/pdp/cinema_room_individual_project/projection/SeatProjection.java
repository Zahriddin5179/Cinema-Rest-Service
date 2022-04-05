package uz.pdp.cinema_room_individual_project.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room_individual_project.model.Seat;

import java.util.UUID;
//@Projection(name = "SeatProjection", types = {Seat.class})
public interface SeatProjection {
    UUID getId();
    Integer getNumber();
    Integer getRowNumber();
    String getHallName();
    String getSeatColor();
}
