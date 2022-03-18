package uz.pdp.cinema_room_individual_project.projection;

import java.time.LocalDate;
import java.util.UUID;

public interface MovieProjection {
    UUID getId();
    String getTitle();
    UUID getPosterImgId();
    LocalDate getreleaseDate();
}
