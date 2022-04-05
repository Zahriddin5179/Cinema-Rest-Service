package uz.pdp.cinema_room_individual_project.projection;

import java.util.UUID;

public interface TicketProjection {
    UUID getId();
    Double getPrice();
    String getMovieTitle();
}
