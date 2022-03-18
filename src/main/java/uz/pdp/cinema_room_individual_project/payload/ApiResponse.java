package uz.pdp.cinema_room_individual_project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;

    private boolean isSuccess;

    private Object data;
}
