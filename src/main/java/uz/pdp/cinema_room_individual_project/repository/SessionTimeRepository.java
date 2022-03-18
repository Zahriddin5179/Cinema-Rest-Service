package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room_individual_project.model.SessionTime;
import uz.pdp.cinema_room_individual_project.projection.SessionTimeProjection;

import java.util.List;
import java.util.UUID;

public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {
    @Query(nativeQuery = true,value = "select cast(st.id as varchar) as id,\n" +
            "       cast(st.time as varchar) as time\n" +
            "       from session_times st\n" +
            "join reserved_halls rh on st.id = rh.start_time_id\n" +
            "join halls h on rh.hall_id = h.id\n" +
            "where h.id=:hallId")
    List<SessionTimeProjection> getTimesByHallId(UUID hallId);
}
