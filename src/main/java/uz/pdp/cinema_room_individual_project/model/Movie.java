package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movies")
public class Movie extends AbsEntity {
    private String title;

    @Column(columnDefinition = "text")
    private String description;
    private Integer durationInMin;
    private Double ticketInitPrice;
    private String trailVideoUrl;
    @OneToOne
    private Attachment posterImg;
    @Column(nullable = false,columnDefinition = "date")
    private LocalDate releaseDate;
    private Double budget;
    private Double distributerShareInPersentage;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Distributor distributorBy;



    @ManyToMany
    List<Genre> genres;

    @ManyToMany
    List<Cast> casts;

}
