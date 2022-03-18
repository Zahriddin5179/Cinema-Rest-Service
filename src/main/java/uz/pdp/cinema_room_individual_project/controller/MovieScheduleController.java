package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room_individual_project.service.MovieScheduleServiseImpl;

import javax.swing.text.html.parser.Entity;
import static uz.pdp.cinema_room_individual_project.utils.Constants.*;
@RestController
@RequestMapping("api/movie-schedule")
public class MovieScheduleController {
    @Autowired
    MovieScheduleServiseImpl movieScheduleServise;
    @GetMapping
    public HttpEntity<?> getAllMovieSchedules(@RequestParam(name = "size",defaultValue = DEFAULT_PAGE_SIZE)Integer size,
                                              @RequestParam(name = "page",defaultValue = "1")Integer page,
                                              @RequestParam(name = "search",defaultValue = "")String search,
                                              @RequestParam(name = "direction",defaultValue = "true")boolean direction,
                                              @RequestParam(name = "sort",defaultValue = "title") String sort
    ){
        return movieScheduleServise.getAllMovieSchedules(size,page,search,sort,direction);
    }
}
