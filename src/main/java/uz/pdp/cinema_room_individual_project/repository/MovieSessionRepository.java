package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.MovieSession;

import java.util.Optional;
import java.util.UUID;
@Component
public interface MovieSessionRepository extends JpaRepository<MovieSession, UUID> {
}
