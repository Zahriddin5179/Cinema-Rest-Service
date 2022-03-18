package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "billing_infos")
public class BillingInfo extends AbsEntity {
    @ManyToOne
    private User user;
    private String cardHolderName;
    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvcCode;

}
