package uz.pdp.cinema_room_individual_project.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.MovieAnnouncement;
import uz.pdp.cinema_room_individual_project.projection.MovieAllDetailProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieAnnouncementProjection;

import java.util.UUID;


@Component
public interface MovieAnnouncementsRepository extends PagingAndSortingRepository<MovieAnnouncement, UUID> {
    @Query(value = "select " +
            "cast(ms.id as varchar) as id,\n" +
            "       sd.date as startDate,\n" +
            "       cast(a.id as varchar) as moviePosterImgId,\n" +
            "        cast(m.id as varchar) as movieId,\n" +
            "        m.title as movieTitle\n" +
            "from movies m\n" +
            "join movie_announcements ms on m.id = ms.movie_id\n" +
            "join movie_sessions rh on ms.id = rh.movie_announcements_id\n" +
            "join session_dates sd on sd.id = rh.session_date_id\n" +
            "join session_times st on  st.id = rh.start_time_id\n" +
            "join attachments a on a.id = m.poster_img_id\n" +
            "where ms.is_active=true\n" +
            "  and lower(m.title) like concat('%',lower(:search),'%')\n" +
            "group by ms.id,m.id,sd.id,a.id",
            nativeQuery = true)
    Page<MovieAnnouncementProjection>getAllMovieAnnouncements(Pageable pageable, String search);
}
