package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.dto.TicketDto;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;
import uz.pdp.cinema_room_individual_project.service.TicketServiseImpl;
import uz.pdp.cinema_room_individual_project.service.TicketServiseImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    TicketServiseImpl ticketServise;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add-ticket")
    public HttpEntity<?>addTicketToCart(@RequestBody TicketDto ticketDto){
        return ticketServise.addTicketToCaart(ticketDto.getMovieSessionId(),ticketDto.getSeatId());
    }

    @GetMapping("/purchase")
    public HttpEntity<?>purchaseTicket(){
        User user = userRepository.findByUsername("zahriddin007");
        return ticketServise.createStripeSession(user.getId());
    }
    @GetMapping("/my-tickets/{userId}")
    public HttpEntity<?>getUserTickets(@PathVariable(name = "userId")UUID userId){
        return ticketServise.getUserTickets(userId);
    }
    @PostMapping("/refund")
    public HttpEntity<?>refundTicket(@RequestBody List<TicketDto>ticketDtoList){
        return ticketServise.refundTicket(ticketDtoList);
    }
}
