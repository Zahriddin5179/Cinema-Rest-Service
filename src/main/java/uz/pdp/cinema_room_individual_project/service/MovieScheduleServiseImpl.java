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
import uz.pdp.cinema_room_individual_project.dto.MovieScheduleDto;
import uz.pdp.cinema_room_individual_project.interfaces.MovieScheduleService;
import uz.pdp.cinema_room_individual_project.interfaces.MovieService;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieScheduleProjection;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MovieScheduleServiseImpl implements MovieScheduleService {
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
    @Autowired
    MovieScheduleRepository movieScheduleRepository;
    @Override
    public HttpEntity getAllMovieSchedules(int size, int page, String search, String sort, boolean direction) {
                try {

            Pageable pageable = PageRequest.of(
                    page-1,
                    size,
                    direction ? Sort.Direction.ASC: Sort.Direction.DESC,
                    sort
            );
            Page<MovieScheduleProjection> allMovieSchedules = movieScheduleRepository.getAllMovieSchedules(pageable,search);
            return ResponseEntity.ok(new ApiResponse("status",true,allMovieSchedules));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse("failed",false,null));
        }
    }

    @Override
    public HttpEntity getMovieScheduleById(UUID id) {
        return null;
    }

    @Override
    public HttpEntity saveMovieSchedule(MovieScheduleDto movieScheduleDto) {
        return null;
    }

    @Override
    public HttpEntity deleteMovieSchedule(UUID id) {
        return null;
    }


//
//    @Override
//    public HttpEntity getMovieById(UUID id) {
//        return null;
//    }
//
//    @Override
//    public HttpEntity saveMovie(MovieDto movieDto) {
//        try {
////            List<Genre> allGenres = genreRepository.getAllGenres(movieDto.getGenresIds());
//            Distributor distributor = distributorRepository.getById(movieDto.getDistributorId());
////            List<Cast> castList = castRepository.getAllCasts(movieDto.getCastsIds());
//            MultipartFile multipartFile = movieDto.getMultipartFile();
//        Attachment attachment = new Attachment(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getSize());
//        attachmentRepository.save(attachment);
//            AttachmentContent attachmentContent = new AttachmentContent(multipartFile.getBytes(), attachment);
//            attachmentContentRepository.save(attachmentContent);
//            Movie movie = new Movie(movieDto.getTitle(), movieDto.getDescription(),
//                    movieDto.getDurationInMin(), movieDto.getTicketInitPrice(),
//                    movieDto.getTrailVideoUrl(), attachment, movieDto.getReleaseDate(),
//                    movieDto.getBudget(), movieDto.getDistributerShareInPersentage(), distributor, null, null);
//            movieRepository.save(movie);
//            return ResponseEntity.ok(new ApiResponse("success",true,movie));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.ok(new ApiResponse("fail",false,null));
//        }
//    }
//
//    @Override
//    public HttpEntity deleteMovie(UUID id) {
//                movieRepository.deleteById(id);
//        return ResponseEntity.ok(new ApiResponse("succes",true,null));
//    }
//    public List<Cast> getAllCasts(UUID[] castsIds){
//        List<Cast> castList = new ArrayList<>();
//        for (UUID castsId : castsIds) {
//            castList.add(castRepository.findById(castsId).get());
//        }
//        return castList;
//    }
}
