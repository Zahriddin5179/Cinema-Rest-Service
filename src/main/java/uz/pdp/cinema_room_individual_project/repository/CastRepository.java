package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.model.AttachmentContent;
import uz.pdp.cinema_room_individual_project.model.Cast;
import uz.pdp.cinema_room_individual_project.projection.CastProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public interface CastRepository extends JpaRepository<Cast,UUID> {
    @Query(nativeQuery = true,value = "select cast(c.id as varchar) as Id,\n" +
            "       c.full_name as castFullName,\n" +
            "       c.cast_type as castType,\n" +
            "       cast(c.attachment_id as varchar) as castImgId\n" +
            "       from casts c\n" +
            "join movies_casts mc on c.id = mc.casts_id\n" +
            "join movies m on m.id = mc.movies_id\n" +
            "where m.id=:movieId")
    List<CastProjection>findByMovieId(UUID movieId);
}
