package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.User;

import java.util.UUID;
@Component
public interface UserRepository extends JpaRepository<User,UUID> {
    User findByUsername(String username);
}
