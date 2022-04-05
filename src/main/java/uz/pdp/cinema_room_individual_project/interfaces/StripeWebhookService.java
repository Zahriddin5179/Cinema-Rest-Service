package uz.pdp.cinema_room_individual_project.interfaces;

import com.stripe.model.checkout.Session;

public interface StripeWebhookService {
    Object handle(String payload,String sigHeader);
    void fullFillOrder(Session session);
}
