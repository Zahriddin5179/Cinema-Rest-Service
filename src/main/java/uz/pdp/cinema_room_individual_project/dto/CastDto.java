package uz.pdp.cinema_room_individual_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.enums.CastType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CastDto {
    private UUID id;
    private String fullName;
    private CastType castType;
}
