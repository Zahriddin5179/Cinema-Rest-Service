package uz.pdp.cinema_room_individual_project.projection;

import java.sql.Time;
import java.util.UUID;

public interface SessionTimeProjection {
    UUID getId();
    String getTime();
}
