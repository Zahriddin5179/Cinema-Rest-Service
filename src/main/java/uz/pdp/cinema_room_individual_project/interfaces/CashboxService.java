package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;

public interface CashboxService {
    HttpEntity<?> getCashboxBalance();
    void chargeFromCashbox(double totalSum);
}
