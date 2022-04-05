package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room_individual_project.interfaces.PurchaseHistoryService;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import uz.pdp.cinema_room_individual_project.projection.PurchaseHistoryProjection;
import uz.pdp.cinema_room_individual_project.repository.PurchaseHistoryRepository;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public HttpEntity<?> userPurchaseHistory(int size, int page) {
        User user = userRepository.findByUsername("zahriddin007");
        try {
            Pageable pageable = PageRequest.of(
                    page-1,
                    size
            );
            Page<PurchaseHistoryProjection> purchaseHistory = purchaseHistoryRepository.getUserPurchaseHistory(pageable, user.getId());
            return ResponseEntity.ok(new ApiResponse("Success",true,purchaseHistory));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("failed",false,null));
        }
    }
}
