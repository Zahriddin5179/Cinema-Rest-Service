package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room_individual_project.model.SessionDate;
import uz.pdp.cinema_room_individual_project.model.SessionTime;
import uz.pdp.cinema_room_individual_project.projection.SessionTimeProjection;

import java.util.List;
import java.util.UUID;

public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {
    @Query(nativeQuery = true,value = "select cast(st.id as varchar) as id,\n" +
            "       st.time as sessionTime,\n" +
            "       cast(ms.id as varchar) as movieSessionId\n" +
            "       from session_times st\n" +
            "join movie_sessions ms on st.id = ms.start_time_id\n" +
            "where ms.hall_id=:hallId")
    List<SessionTimeProjection>getTimesByHallId(UUID hallId);
}
