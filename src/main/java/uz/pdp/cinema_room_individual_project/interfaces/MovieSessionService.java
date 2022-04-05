package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.dto.MovieSessionDto;

import java.util.UUID;

public interface MovieSessionService {
    HttpEntity<?> getAllMovieSessions(int size,int page,String search,String sort,boolean direction);
    HttpEntity<?> saveMovieSession(MovieSessionDto movieSessionDto);
    HttpEntity<?> deleteMovieSession(UUID id);
    HttpEntity<?> editMovieSession(UUID id);
}
