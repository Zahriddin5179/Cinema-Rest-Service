package uz.pdp.cinema_room_individual_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {
    private UUID id;
    private UUID movieSessionId;
    private UUID seatId;
}
