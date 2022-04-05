package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room_individual_project.dto.MovieSessionDto;
import uz.pdp.cinema_room_individual_project.service.MovieSessionServiseImpl;

@RestController
@RequestMapping("/api/movie-session")
public class MovieSessionController {
    @Autowired
    MovieSessionServiseImpl movieSessionServise;
    @PostMapping
    public HttpEntity<?>saveMovieSession(@RequestBody MovieSessionDto movieSessionDto){
        return movieSessionServise.saveMovieSession(movieSessionDto);
    }

}

