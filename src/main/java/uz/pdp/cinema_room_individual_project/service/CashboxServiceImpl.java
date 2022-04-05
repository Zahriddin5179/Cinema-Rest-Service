package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room_individual_project.interfaces.CashboxService;
import uz.pdp.cinema_room_individual_project.model.CashBox;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.repository.CashboxRepository;
@Service
public class CashboxServiceImpl implements CashboxService {
    @Autowired
    CashboxRepository cashboxRepository;
    @Override
    public HttpEntity<?> getCashboxBalance() {
        CashBox cashbox = cashboxRepository.findByName("Cashbox");
        Double balance = cashbox.getBalance();
        return ResponseEntity.ok(new ApiResponse("Succes",true,balance));
    }

    @Override
    public void chargeFromCashbox(double totalSum) {
        CashBox cashbox = cashboxRepository.findByName("Cashbox");
        cashbox.setBalance(cashbox.getBalance()- totalSum);
        cashboxRepository.save(cashbox);
    }
}
