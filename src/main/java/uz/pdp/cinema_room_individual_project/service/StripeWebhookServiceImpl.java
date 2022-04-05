package uz.pdp.cinema_room_individual_project.service;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room_individual_project.dto.MailDto;
import uz.pdp.cinema_room_individual_project.enums.TicketStatus;
import uz.pdp.cinema_room_individual_project.interfaces.CashboxService;
import uz.pdp.cinema_room_individual_project.interfaces.StripeWebhookService;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;
import uz.pdp.cinema_room_individual_project.repository.CashboxRepository;
import uz.pdp.cinema_room_individual_project.repository.PurchaseHistoryRepository;
import uz.pdp.cinema_room_individual_project.repository.TicketRepository;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StripeWebhookServiceImpl implements StripeWebhookService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CashboxRepository cashboxRepository;
    @Autowired
    SendEmailServiceImpl sendEmailService;


    @Value("${STRIPE_SECURITY_KEY}")
    String apiKey;

    String endPointSecret = "whsec_92e419b6feb7f645d7e3048a55451b9129aa8e1e83038da08d9f3f6b2aefcb27";
    @SneakyThrows
    @Override
    public Object handle(String payload, String sigHeader) {
        System.out.println("========================KELDI===========================");

        Stripe.apiKey=apiKey;

        System.out.println("Payload " + payload);

        Event event = null;
        event = Webhook.constructEvent(payload, sigHeader, endPointSecret);

        if ("checkout.session.completed".equals(event.getType())){
            Session session =(Session) event.getDataObjectDeserializer().getObject().get();
            fullFillOrder(session);
        }
        return "";
    }

    @Override
    public void fullFillOrder(Session session) {
        double initPrice = 0;
        List<Ticket> tickets = new ArrayList<>();
        String clientReferenceId = session.getClientReferenceId();

        List<TicketProjection> listTicket = ticketRepository.listTicket(UUID.fromString(clientReferenceId));
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(session.getClientReferenceId()));

        for (TicketProjection ticket : listTicket) {
            Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
            Ticket ticket1 = ticketOptional.get();
            ticket1.setTicketStatus(TicketStatus.PURCHASED);
            Ticket saveTicket = ticketRepository.save(ticket1);

            if (optionalUser.isPresent() && ticketOptional.isPresent()){
                tickets.add(saveTicket);
            }
            initPrice += ticket.getPrice();
        }


        PurchaseHistory purchaseHistory = new PurchaseHistory(tickets,null,initPrice,false,session.getPaymentIntent());
        purchaseHistoryRepository.save(purchaseHistory);

        CashBox cashbox = cashboxRepository.findByName("Cashbox");
        cashbox.setBalance(cashbox.getBalance() +initPrice);
        cashboxRepository.save(cashbox);
        sendEmailService.sendMessage(listTicket);

//        new Thread(() ->{
//        }).start();
    }
}
