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
@Entity(name = "movie_sessions")
public class MovieSession extends AbsEntity {
    @ManyToOne
    private Hall hall;
    @ManyToOne
    private SessionDate sessionDate;
    @ManyToOne
    private SessionTime startTime;
    @ManyToOne
    private SessionTime endTime;
    @ManyToOne
    private MovieAnnouncement movieAnnouncements;

}
