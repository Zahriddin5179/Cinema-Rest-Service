package uz.pdp.cinema_room_individual_project.interfaces;

import com.stripe.model.checkout.Session;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;

import java.util.UUID;

public interface TicketService {
    HttpEntity addTicketToCaart(UUID movieSessionId,UUID seatId);
    HttpEntity<?>getUserTickets(UUID userId);
    HttpEntity<?>createStripeSession(UUID currentUserId);
}
