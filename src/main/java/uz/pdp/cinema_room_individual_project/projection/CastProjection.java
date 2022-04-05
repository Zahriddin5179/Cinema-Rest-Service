package uz.pdp.cinema_room_individual_project.projection;

import java.util.UUID;
public interface CastProjection {
    String getId();
    String getCastType();
    String getCastFullName();
    UUID getCastImgId();
}
