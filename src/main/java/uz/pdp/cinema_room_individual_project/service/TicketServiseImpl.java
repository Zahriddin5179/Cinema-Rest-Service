package uz.pdp.cinema_room_individual_project.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RecipientCreateParams;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room_individual_project.dto.TicketDto;
import uz.pdp.cinema_room_individual_project.enums.TicketStatus;
import uz.pdp.cinema_room_individual_project.interfaces.TicketService;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.TicketProjection;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiseImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    SeatRepository seatRepository;
    MovieSessionRepository movieSessionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    CashboxServiceImpl cashboxService;
    @Autowired
    SendEmailServiceImpl sendEmailService;


    @Override
    public HttpEntity<?> addTicketToCaart(UUID movieSessionId, UUID seatId) {
        try {
            System.out.println(movieSessionId);
            System.out.println(seatId);

            Ticket ticket = new Ticket();
            Optional<User> optionalUser = userRepository.findById(UUID.fromString("03faabe7-ed40-45fc-99b6-5e3a291023bb"));
            MovieSession movieSession = movieSessionRepository.findById(movieSessionId).orElseThrow(() -> new ResourceNotFoundException("Session not found!"));
            Optional<Seat> optionalSeat = seatRepository.findById(seatId);

//            if (optionalMovieSession.isPresent()){
//                ticket.setMovieSession(optionalMovieSession.get());
//            }

            ticket.setMovieSession(movieSession);
            optionalSeat.ifPresent(ticket::setSeat);

            Double ticketTotalPrice = ticketRepository.getTicketTotalPrice(movieSessionId,seatId);
            ticket.setPrice(ticketTotalPrice);
            ticket.setTicketStatus(TicketStatus.NEW);


            if (optionalUser.isPresent()) {
                ticket.setUser(optionalUser.get());
            }

            ticketRepository.save(ticket);
            return ResponseEntity.ok(new ApiResponse("Success", true, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("Failed", false, null));
        }
    }

    @Override
    public HttpEntity<?> getUserTickets(UUID userId) {
        List<TicketProjection> ticketProjections = ticketRepository.listTicket(userId);
        return ResponseEntity.ok(new ApiResponse("Succes", true, ticketProjections));
    }

    @SneakyThrows
    @Override
    public HttpEntity<?> createStripeSession(UUID currentUserId) {
        Stripe.apiKey = stripeApiKey;

        List<TicketProjection> ticketProjections = ticketRepository.listTicket(currentUserId);

        ArrayList<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (TicketProjection ticketProjection : ticketProjections) {

            Double fFixed = 0.3;
            Double price = ticketProjection.getPrice();
            Double totalPrice = (price + fFixed) / (1 - 0.029);
            String title = ticketProjection.getMovieTitle();
            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams
                    .LineItem.PriceData.ProductData
                    .builder()
                    .setName(title)
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setCurrency("USD")
                    .setUnitAmount((long) (totalPrice * 100))
                    .setProductData(productData)
                    .build();
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();

            lineItems.add(lineItem);
        }
        SessionCreateParams params = SessionCreateParams
                .builder()
                .addAllLineItem(lineItems)
                .setCancelUrl("http://localhost:80/paymment-failed")
                .setSuccessUrl("http://localhost:80/payment-succedeed")
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(currentUserId.toString())
                .build();
        Session session = Session.create(params);
        String url = null;
        if (session != null) {
            url = session.getUrl();
        }
        return ResponseEntity.ok(url);
    }


    public HttpEntity<?> purchaseTickets() {
        return null;
    }

    @Value("${STRIPE_SECURITY_KEY}")
    String stripeApiKey;

    public HttpEntity<?> refundTicket(List<TicketDto> ticketDtoList) {
        double totalTicketSum = 0;
        for (TicketDto ticketDto : ticketDtoList) {
            double sum = ticketRepository.totalSum(ticketDto.getId());
            totalTicketSum += sum;
        }
        Stripe.apiKey = stripeApiKey;
        String intent = purchaseHistoryRepository.findPurchaseHistoryIntentByTicketId(ticketDtoList.get(0).getId());
        RefundCreateParams params = RefundCreateParams
                .builder()
                .setPaymentIntent(intent)
                .setAmount((long) totalTicketSum)
                .build();
        try {
            Refund refund = Refund.create(params);
            if (refund.getStatus().equals("Success")) {
                cashboxService.chargeFromCashbox(totalTicketSum);
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ApiResponse("Succes",true,null));
    }
}
