package uz.pdp.cinema_room_individual_project.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room_individual_project.dto.MailDto;
import uz.pdp.cinema_room_individual_project.model.User;
import uz.pdp.cinema_room_individual_project.repository.PurchaseHistoryRepository;
import uz.pdp.cinema_room_individual_project.repository.UserRepository;
import uz.pdp.cinema_room_individual_project.service.PurchaseHistoryServiceImpl;
import uz.pdp.cinema_room_individual_project.service.SendEmailServiceImpl;

import static uz.pdp.cinema_room_individual_project.utils.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/purchase-history")
public class PurchaseHistoryController {
    @Autowired
    PurchaseHistoryServiceImpl purchaseHistoryService;
    @Autowired
    SendEmailServiceImpl sendEmailService;

    @GetMapping
    public HttpEntity<?>userPurchaseHistory(@RequestParam(name = "size",defaultValue= DEFAULT_PAGE_SIZE) int size,
                                            @RequestParam(name = "page",defaultValue="1") int page){
        return purchaseHistoryService.userPurchaseHistory(size,page);
    }
    @SneakyThrows
    @GetMapping("/email")
    public HttpEntity<?> sendMail(MailDto mailDto){
        return sendEmailService.sendTemplate();
    }
}
