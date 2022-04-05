package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "seats")
public class Seat extends AbsEntity {

    private Integer number;
    @ManyToOne
    private Row row;

    @ManyToOne
    private PriceCategory priceCategory;

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", priceCategory=" + priceCategory +
                '}';
    }
}
