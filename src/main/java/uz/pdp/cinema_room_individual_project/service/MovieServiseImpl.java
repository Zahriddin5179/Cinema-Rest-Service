package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.interfaces.MovieService;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.MovieAllDetailProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.io.IOException;
import java.util.*;

@Service
public class MovieServiseImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    DistributorRepository distributorRepository;
    @Autowired
    CastRepository castRepository;
    @Autowired
    GenreRepository genreRepository;


    @Override
    public HttpEntity<?> getAllMovies(int size, int page, String search, String sort, boolean direction) {
        try {

            Pageable pageable = PageRequest.of(
                    page-1,
                    size,
                    direction ? Sort.Direction.ASC: Sort.Direction.DESC,
                    sort
            );
            Page<MovieProjection> allMovies = movieRepository.getAllMovies(pageable,search);
            return ResponseEntity.ok(new ApiResponse("Succes",true,allMovies));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("failed",false,null));
        }
    }

    @Override
    public HttpEntity<?> getMovieById(UUID id) {
        try{
            MovieAllDetailProjection movieById = movieRepository.getMovieById(id);
            return ResponseEntity.ok(new ApiResponse("Succes",true,movieById));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("Failed",false,null));
        }
    }

    @Override
    public HttpEntity<?>saveMovie(MultipartFile file, MovieDto movieDto) {
        try {
            List<Genre> allGenres = genreRepository.findAllById(movieDto.getGenresIds());
            List<Cast> castList = castRepository.findAllById(movieDto.getCastsIds());
//            Distributor distributor = distributorRepository.getById(movieDto.getDistributorId());
            Optional<Distributor> byId = distributorRepository.findById(movieDto.getDistributorId());
            Distributor distributor = null;
            if (byId.isPresent()) {
                 distributor=byId.get();
            }
            Attachment attachment = new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize());
        attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), attachment);
            attachmentContentRepository.save(attachmentContent);
            Movie movie = new Movie(movieDto.getTitle(), movieDto.getDescription(),
                    movieDto.getDurationInMin(), movieDto.getTicketInitPrice(),
                    movieDto.getTrailVideoUrl(), attachment,movieDto.getReleaseDate(),
                    movieDto.getBudget(), movieDto.getDistributerShareInPersentage(), distributor, allGenres, castList);
            movieRepository.save(movie);
            return ResponseEntity.ok(new ApiResponse("success",true,movie));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("fail",false,null));
        }
    }

    @Override
    public HttpEntity deleteMovie(UUID id) {
                movieRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("success",true,null));
    }
    public List<Cast> getAllCasts(UUID[] castsIds){
        List<Cast> castList = new ArrayList<>();
        for (UUID castsId : castsIds) {
            castList.add(castRepository.findById(castsId).get());
        }
        return castList;
    }
}
