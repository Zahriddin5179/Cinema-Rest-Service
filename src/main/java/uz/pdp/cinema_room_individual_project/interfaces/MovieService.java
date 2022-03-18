package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;

import java.util.UUID;

public interface MovieService {
    HttpEntity getAllMovies(int size,int page,String search,String sort,boolean direction);
    HttpEntity getMovieById(UUID id);
    HttpEntity saveMovie(MultipartFile file, MovieDto movieDto);
    HttpEntity deleteMovie(UUID id);
}
