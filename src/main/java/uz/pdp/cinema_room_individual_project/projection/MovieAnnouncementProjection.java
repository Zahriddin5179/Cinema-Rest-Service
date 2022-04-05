package uz.pdp.cinema_room_individual_project.projection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room_individual_project.model.Hall;
import uz.pdp.cinema_room_individual_project.model.Movie;
import uz.pdp.cinema_room_individual_project.repository.HallRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
public interface MovieAnnouncementProjection {
    UUID getId();
    LocalDate getStartDate();
    UUID getMoviePosterImgId();
    UUID getMovieId();
    String getMovieTitle();
    @Value("#{@hallRepository.getHallsByMovieId(target.movieId)}")
    List<HallProjection> getHalls();
}
