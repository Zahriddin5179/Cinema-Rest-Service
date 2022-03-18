package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.service.MovieServiseImpl;

import java.util.List;
import java.util.UUID;

import static uz.pdp.cinema_room_individual_project.utils.Constants.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieServiseImpl movieServise;
    @GetMapping
    public HttpEntity<?> getAllMovies(@RequestParam(name = "size",defaultValue= DEFAULT_PAGE_SIZE) int size,
                                      @RequestParam(name = "page",defaultValue="1") int page,
                                      @RequestParam(name = "search", defaultValue="") String search,
                                      @RequestParam(name = "direction",defaultValue = "true")boolean direction,
                                      @RequestParam(name = "sort",defaultValue="title") String sort
                                          ){
       return movieServise.getAllMovies(size, page, search, sort, direction);
    }
    @PostMapping
    public HttpEntity<?> saveMovie(@RequestPart("json") MovieDto movieDto,
                                @RequestPart("file")MultipartFile file){
        return movieServise.saveMovie(file,movieDto);
    }
    @DeleteMapping("/{movieId}")
    public HttpEntity<?> deleteMovie(@PathVariable(name = "movieId") UUID movieId){
        return movieServise.deleteMovie(movieId);
    }

}
