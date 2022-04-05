package uz.pdp.cinema_room_individual_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDto {
    private String to;
    private String subject;
    private String text;
}
