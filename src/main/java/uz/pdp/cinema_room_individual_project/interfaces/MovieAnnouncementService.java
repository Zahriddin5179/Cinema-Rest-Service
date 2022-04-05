package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.dto.MovieAnnouncementDto;

import java.util.UUID;

public interface MovieAnnouncementService {
    HttpEntity getAllMovieAnnouncements(int size,int page,String search,String sort,boolean direction);
    HttpEntity<?>getAllAvailableSeats(UUID movieAnnouncementId);
    HttpEntity saveMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto);
    HttpEntity deleteMovieAnnouncement(UUID id);
}
