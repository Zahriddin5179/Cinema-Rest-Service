package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "purchase_histories")
public class PurchaseHistory extends AbsEntity {
        @ManyToMany
    @JoinTable(
            name = "purchase_histories_tickets",
            joinColumns = @JoinColumn(name = "purchase_history_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Ticket> ticket;
    @ManyToOne
    private PayType payType;
    private Double amount;
    private boolean isRefunded;
    private String stripePaymentIntent;
}
