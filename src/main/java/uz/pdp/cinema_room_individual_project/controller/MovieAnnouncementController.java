package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room_individual_project.service.MovieAnnouncementServiseImpl;

import java.util.UUID;

import static uz.pdp.cinema_room_individual_project.utils.Constants.*;
@RestController
@RequestMapping("/api/movie-announcement")
public class MovieAnnouncementController {
    @Autowired
    MovieAnnouncementServiseImpl movieAnnouncementServise;
    @GetMapping
    public HttpEntity<?> getAllMovieAnnouncements(@RequestParam(name = "size",defaultValue = DEFAULT_PAGE_SIZE)Integer size,
                                              @RequestParam(name = "page",defaultValue = "1")Integer page,
                                              @RequestParam(name = "search",defaultValue = "")String search,
                                              @RequestParam(name = "direction",defaultValue = "true")boolean direction,
                                              @RequestParam(name = "sort",defaultValue = "title") String sort
    ){
        return movieAnnouncementServise.getAllMovieAnnouncements(size,page,search,sort,direction);
    }
    @GetMapping("/available-seats/{movieSessionId}")
    public HttpEntity<?>getAvailableSeats(@PathVariable(required = false,name = "movieSessionId")UUID movieSessionId){
       return movieAnnouncementServise.getAllAvailableSeats(movieSessionId);
    }
}
