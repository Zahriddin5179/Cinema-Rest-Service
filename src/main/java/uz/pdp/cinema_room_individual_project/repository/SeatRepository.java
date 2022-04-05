package uz.pdp.cinema_room_individual_project.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Hall;
import uz.pdp.cinema_room_individual_project.model.Seat;
import uz.pdp.cinema_room_individual_project.projection.SeatProjection;

import java.util.List;
import java.util.UUID;

@Component
public interface SeatRepository extends JpaRepository<Seat,UUID> {
    @Query(nativeQuery = true, value = "select cast(s.id as varchar) as id,s.number as number,r.number as rowNumber,h.name as hallName,pc.color as seatColor\n" +
            "            from seats s\n" +
            "            join rowss r on r.id = s.row_id\n" +
            "            join halls h on h.id = r.hall_id\n" +
            "            join movie_sessions ms on h.id = ms.hall_id\n" +
            "            join movie_announcements ma on ma.id = ms.movie_announcements_id\n" +
            "            join price_category pc on pc.id = s.price_category_id\n" +
            "            where ms.id=:movieSessionId and ma.is_active=true\n" +
            "            except\n" +
            "            select cast(s2.id as varchar),s2.number,r2.number,h2.name,pc.color\n" +
            "            from tickets t\n" +
            "            join seats s2 on t.seat_id = s2.id\n" +
            "            join rowss r2 on s2.row_id = r2.id\n" +
            "            join halls h2 on r2.hall_id = h2.id\n" +
            "            join price_category pc on s2.price_category_id = pc.id\n" +
            "            where t.ticket_status='NEW' or t.ticket_status='PURCHASED'")
    List<SeatProjection>getAllAvailableSeats(UUID movieSessionId);
}
