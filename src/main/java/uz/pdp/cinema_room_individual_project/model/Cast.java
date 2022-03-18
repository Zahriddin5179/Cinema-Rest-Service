package uz.pdp.cinema_room_individual_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room_individual_project.enums.CastType;
import uz.pdp.cinema_room_individual_project.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "casts")
public class Cast extends AbsEntity {
    private String fullName;
    @OneToOne
    private Attachment attachment;
    @Enumerated(EnumType.STRING)
    private CastType castType;

    public Cast(String fullName, Attachment attachment) {
        this.fullName = fullName;
        this.attachment = attachment;
    }
}
