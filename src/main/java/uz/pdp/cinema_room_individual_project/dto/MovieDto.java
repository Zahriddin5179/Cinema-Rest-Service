package uz.pdp.cinema_room_individual_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private UUID id;
    private String title;
    private Double budget;
    private String description;
    private Double distributerShareInPersentage;
    private Integer durationInMin;
    private Double ticketInitPrice;
    private String trailVideoUrl;
    private Date releaseDate;
    private UUID distributorId;
    private List<UUID> castsIds;
    private List<UUID> genresIds;
}
