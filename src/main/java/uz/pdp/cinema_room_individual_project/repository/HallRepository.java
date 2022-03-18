package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Hall;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.projection.HallProjection;

import java.util.List;
import java.util.UUID;

@Component
public interface HallRepository extends JpaRepository<Hall,UUID> {
    @Query(nativeQuery = true,value ="select h.name as name,cast(h.id as varchar)as id from halls h\n" +
            "join reserved_halls rh on h.id = rh.hall_id\n" +
            "join movie_schedule ms on rh.afisha_id = ms.id\n" +
            "join movies m on ms.movie_id = m.id\n" +
            "where m.id=:movieId")
    List<HallProjection> getHallsByMovieId(UUID movieId);
}
