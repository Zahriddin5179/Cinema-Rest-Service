package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room_individual_project.model.SessionDate;

import java.util.UUID;

public interface SessionDateRepository extends JpaRepository<SessionDate, UUID> {
}
