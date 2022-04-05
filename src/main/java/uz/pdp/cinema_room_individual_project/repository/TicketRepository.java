package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.enums.TicketStatus;
import uz.pdp.cinema_room_individual_project.model.Ticket;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;

import java.util.List;
import java.util.UUID;

@Component
public interface TicketRepository extends JpaRepository<Ticket,UUID> {
    @Query(nativeQuery = true,value = "select * from get_ticket_total_price(:movieSessionId,:seatId)")
    Double getTicketTotalPrice(UUID movieSessionId,UUID seatId);

    @Query(nativeQuery = true,value = "select cast(t.id as varchar) as id,\n" +
            "                   t.price as price,\n" +
            "                   m.title as movieTitle\n" +
            "                   from tickets t\n" +
            "            join users u on t.user_id = u.id\n" +
            "            join movie_sessions ms on t.movie_session_id = ms.id\n" +
            "            join movie_announcements ma on ma.id = ms.movie_announcements_id\n" +
            "            join movies m on m.id = ma.movie_id\n" +
            "            where u.id=:userId and t.ticket_status='NEW'")
    List<TicketProjection>listTicket(UUID userId);


    List<Ticket>findAllByUserIdAndTicketStatus(UUID userId,TicketStatus ticketStatus);

    @Query(nativeQuery = true,value = "select * from calc_comission_fee(:ticketId)")
    double totalSum(UUID ticketId);
}
