package uz.pdp.cinema_room_individual_project.interfaces;

import org.springframework.http.HttpEntity;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.dto.MovieScheduleDto;

import java.util.UUID;

public interface MovieScheduleService {
    HttpEntity getAllMovieSchedules(int size,int page,String search,String sort,boolean direction);
    HttpEntity getMovieScheduleById(UUID id);
    HttpEntity saveMovieSchedule(MovieScheduleDto movieScheduleDto);
    HttpEntity deleteMovieSchedule(UUID id);
}
