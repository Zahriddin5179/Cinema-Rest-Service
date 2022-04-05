package uz.pdp.cinema_room_individual_project.model;

import lombok.*;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rowss")
public class Row extends AbsEntity {

    private Integer number;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "row",cascade =CascadeType.ALL)
    private List<Seat> seatList;

    public Row(Integer number, Hall hall) {
        this.number=number;
        this.hall=hall;
    }

    @Override
    public String toString() {
        return "Row{" +
                "number=" + number +
                '}';
    }
}
