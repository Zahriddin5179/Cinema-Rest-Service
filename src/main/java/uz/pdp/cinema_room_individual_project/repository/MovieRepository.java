package uz.pdp.cinema_room_individual_project.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Movie;
import uz.pdp.cinema_room_individual_project.projection.MovieAllDetailProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import java.util.UUID;
@Component
public interface MovieRepository extends PagingAndSortingRepository<Movie,UUID> {
    @Query(value = "select cast(m.id as varchar) as id,\n" +
            "       m.title as title,\n" +
            "       cast(m.poster_img_id as varchar) as attachmentId,\n" +
            "       m.release_date as releaseDate\n" +
            "from movies m\n" +
            "    WHERE lower(title) like concat('%',lower(:search),'%')",
            nativeQuery = true)
    Page<MovieProjection> getAllMovies(Pageable pageable,String search);

    @Query(nativeQuery = true,value = "select cast(m.id as varchar) as movieId,\n" +
            "       m.title as movieTitle,\n" +
            "       cast(m.poster_img_id as varchar) as moviePosterImg,\n" +
            "       m.budget as movieBudget,\n" +
            "       m.description as movieDescription,\n" +
            "       m.duration_in_min as movieDuration,\n" +
            "       m.release_date as movieReleaseDate,\n" +
            "       m.trail_video_url as movieTrailVideoUrl\n" +
            "from movies m\n" +
            "where m.id=:movieId")
    MovieAllDetailProjection getMovieById(UUID movieId);
}
