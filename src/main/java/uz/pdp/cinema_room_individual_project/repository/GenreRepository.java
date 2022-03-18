package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room_individual_project.model.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
//    List<Genre> getAllGenres(UUID[] genresIds);
}
