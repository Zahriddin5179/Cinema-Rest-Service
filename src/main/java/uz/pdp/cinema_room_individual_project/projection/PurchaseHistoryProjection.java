package uz.pdp.cinema_room_individual_project.projection;

import java.util.UUID;

public interface PurchaseHistoryProjection{
    UUID getId();
    String getMovieTitle();
    UUID getMovieSessionId();
    UUID getSeatId();
    Double getTicketPrice();
}
