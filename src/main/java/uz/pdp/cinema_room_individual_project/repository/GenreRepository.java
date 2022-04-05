package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Genre;
import uz.pdp.cinema_room_individual_project.projection.GenreProjection;

import java.util.List;
import java.util.UUID;
@Component
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    @Query(nativeQuery = true,value = "select cast(g.id as varchar) as id,\n" +
            "       g.name as genreName\n" +
            "       from genres g\n" +
            "join movies_genres mg on g.id = mg.genres_id\n" +
            "join movies m on mg.movies_id = m.id\n" +
            "where m.id=:movieId")
    List<GenreProjection> findByIdMovieId(UUID movieId);
}
