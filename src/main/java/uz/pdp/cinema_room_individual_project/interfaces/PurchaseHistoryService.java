package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;

public interface PurchaseHistoryService {
    HttpEntity<?>userPurchaseHistory(int size, int page);
}
