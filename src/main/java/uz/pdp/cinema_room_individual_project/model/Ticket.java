package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.enums.TicketStatus;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
public class Ticket extends AbsEntity {
    @ManyToOne
    private MovieSchedule movieSchedule;
    @ManyToOne
    private Seat seat;
    @OneToOne
    private Attachment qrCode;
    private Double price;
    @ManyToOne
    private Cart cart;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private Timestamp createdAt;
}
