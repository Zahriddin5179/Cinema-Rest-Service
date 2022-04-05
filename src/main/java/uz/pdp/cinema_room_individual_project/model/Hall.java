package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall extends AbsEntity {
    private UUID id;
    private String name;
    private Integer vipAdditionalFeeInPersent;

    public Hall(String s, int i) {
        this.name = s;
        this.vipAdditionalFeeInPersent = i;
    }

    public Hall(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vipAdditionalFeeInPersent=" + vipAdditionalFeeInPersent +
                '}';
    }
}
