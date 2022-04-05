package uz.pdp.cinema_room_individual_project.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room_individual_project.enums.TicketStatus;
import uz.pdp.cinema_room_individual_project.model.CashBox;
import uz.pdp.cinema_room_individual_project.model.PurchaseHistory;
import uz.pdp.cinema_room_individual_project.model.Ticket;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;
import uz.pdp.cinema_room_individual_project.repository.CashboxRepository;
import uz.pdp.cinema_room_individual_project.repository.PurchaseHistoryRepository;
import uz.pdp.cinema_room_individual_project.repository.TicketRepository;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;
import uz.pdp.cinema_room_individual_project.service.StripeWebhookServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StripeWebhookController {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CashboxRepository cashboxRepository;
    @Autowired
    StripeWebhookServiceImpl stripeWebhookService;


    @RequestMapping(value = "payment-succedeed",method = RequestMethod.GET)
    public HttpEntity<?>getSuccessMsg(){
        return ResponseEntity.ok("Payment Succeeded");
    }

    @RequestMapping(value = "paymment-failed",method = RequestMethod.GET)
    public HttpEntity<?>getFailMsg(){
        return ResponseEntity.ok("Payment Failed");
    }


    @SneakyThrows
    @RequestMapping(value = "stripe-webhook",method = RequestMethod.POST)
    public Object handle(@RequestBody String payload,@RequestHeader("Stripe-Signature")String sigHeader){
        return stripeWebhookService.handle(payload, sigHeader);
    }
}
