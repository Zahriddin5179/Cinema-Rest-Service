package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.PurchaseHistory;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import uz.pdp.cinema_room_individual_project.projection.PurchaseHistoryProjection;
import java.util.List;
import java.util.UUID;
@Component
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, UUID>{
    @Query(value = "select cast(ph.id as varchar) as id,\n" +
            "                   m.title as movieTitle,\n" +
            "                   cast(ms.id as varchar) as movieSessionId,\n" +
            "                   cast(t.seat_id as varchar) as seatId,\n" +
            "                   t.price as ticketPrice\n" +
            "            from purchase_histories ph\n" +
            "                join purchase_histories_tickets pht on ph.id = pht.purchase_history_id\n" +
            "                join tickets t on t.id = pht.ticket_id\n" +
            "            join movie_sessions ms on t.movie_session_id = ms.id\n" +
            "            join movie_announcements ma on ma.id = ms.movie_announcements_id\n" +
            "            join movies m on m.id = ma.movie_id\n" +
            "            join users u on t.user_id = u.id\n" +
            "            where u.id=:userId",
            nativeQuery = true)
    Page<PurchaseHistoryProjection>getUserPurchaseHistory(Pageable pageable,UUID userId);

    @Query(nativeQuery = true,value = "select ph.stripe_payment_intent from purchase_histories ph " +
            "join purchase_histories_tickets pht on ph.id = pht.purchase_history_id" +
            " join tickets t on pht.ticket_id = t.id" +
            " where t.id=:ticketId limit 1")
    String findPurchaseHistoryIntentByTicketId(UUID ticketId);
}
