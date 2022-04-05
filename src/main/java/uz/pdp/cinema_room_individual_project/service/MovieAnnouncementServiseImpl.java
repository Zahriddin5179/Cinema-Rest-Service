package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room_individual_project.dto.MovieAnnouncementDto;
import uz.pdp.cinema_room_individual_project.interfaces.MovieAnnouncementService;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.MovieAllDetailProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieAnnouncementProjection;
import uz.pdp.cinema_room_individual_project.projection.SeatProjection;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.util.List;
import java.util.UUID;

@Service
public class MovieAnnouncementServiseImpl implements MovieAnnouncementService {
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
    MovieAnnouncementsRepository movieAnnouncementRepository;
    @Autowired
    SeatRepository seatRepository;

    @Override
    public HttpEntity<?> getAllMovieAnnouncements(int size, int page, String search, String sort, boolean direction) {
        try {
            Pageable pageable = PageRequest.of(
                    page - 1,
                    size,
                    direction ? Sort.Direction.ASC : Sort.Direction.DESC,
                    sort
            );
            Page<MovieAnnouncementProjection> allMovieAnnouncements = movieAnnouncementRepository.getAllMovieAnnouncements(pageable, search);
            return ResponseEntity.ok(new ApiResponse("status", true, allMovieAnnouncements));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse("failed", false, null));
        }
    }

    @Override
    public HttpEntity<?>getAllAvailableSeats(UUID movieSessionId) {
        try {
            List<SeatProjection> allAvailableSeats = seatRepository.getAllAvailableSeats(movieSessionId);
            return ResponseEntity.ok(new ApiResponse("status", true, allAvailableSeats));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse("failed", false, null));
        }
    }

    @Override
    public HttpEntity<?> saveMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteMovieAnnouncement(UUID id) {
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
