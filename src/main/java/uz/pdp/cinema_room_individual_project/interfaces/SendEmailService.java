package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;
import uz.pdp.cinema_room_individual_project.dto.MailDto;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface SendEmailService {
    HttpEntity<?> sendMessage(List<TicketProjection> ticketProjections);
    HttpEntity<?> sendTemplate() throws MessagingException;
    HttpEntity<?> sendGif(Map<String,Object> templateModel);
}
