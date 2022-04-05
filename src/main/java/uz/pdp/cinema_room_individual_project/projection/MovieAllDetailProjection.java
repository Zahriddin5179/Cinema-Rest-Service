package uz.pdp.cinema_room_individual_project.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MovieAllDetailProjection {
    UUID getMovieId();
    String getMovieTitle();
    UUID getmoviePosterImg();
    Double getMovieBudget();
    String getMovieDescription();
    Integer getMovieDuration();
    Date getMovieReleaseDate();
    String getmovieTrailVideoUrl();
    @Value("#{@castRepository.findByMovieId(target.movieId)}")
    List<CastProjection>getCasts();
    @Value("#{@genreRepository.findByIdMovieId(target.movieId)}")
    List<GenreProjection>getGenres();
}
