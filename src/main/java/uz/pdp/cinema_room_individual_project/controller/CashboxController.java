package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room_individual_project.repository.CashboxRepository;
import uz.pdp.cinema_room_individual_project.service.CashboxServiceImpl;

@RestController
@RequestMapping("/api/cashbox")
public class CashboxController {
    @Autowired
    CashboxServiceImpl cashboxService;

    @GetMapping
    public HttpEntity<?>getCashboxBalance(){
        return cashboxService.getCashboxBalance();
    }
}
