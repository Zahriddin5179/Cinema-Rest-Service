package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Hall;
import uz.pdp.cinema_room_individual_project.model.Row;

import java.util.UUID;

@Component
public interface RowRepository extends JpaRepository<Row,UUID> {

}
