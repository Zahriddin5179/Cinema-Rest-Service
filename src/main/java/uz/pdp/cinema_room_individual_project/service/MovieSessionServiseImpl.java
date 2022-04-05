package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.MovieDto;
import uz.pdp.cinema_room_individual_project.dto.MovieSessionDto;
import uz.pdp.cinema_room_individual_project.dto.ReservedHallDto;
import uz.pdp.cinema_room_individual_project.interfaces.MovieService;
import uz.pdp.cinema_room_individual_project.interfaces.MovieSessionService;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.MovieAllDetailProjection;
import uz.pdp.cinema_room_individual_project.projection.MovieProjection;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class MovieSessionServiseImpl implements MovieSessionService {
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
    MovieSessionRepository movieSessionRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    MovieAnnouncementsRepository movieAnnouncementsRepository;
    @Autowired
    SessionDateRepository sessionDateRepository;
    @Autowired
    SessionTimeRepository sessionTimeRepository;


    @Override
    public HttpEntity<?> getAllMovieSessions(int size, int page, String search, String sort, boolean direction) {
        return null;
    }

    @Override
    public HttpEntity<?> saveMovieSession(MovieSessionDto movieSessionDto) {
        for (ReservedHallDto reservedHall : movieSessionDto.getReservedHalls()) {
            if (reservedHall.getEndDate() != null) {
                LocalDate startDate = LocalDate.parse(reservedHall.getStartDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                LocalDate endDate = LocalDate.parse(reservedHall.getEndDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                long daysBetween = DAYS.between(startDate, endDate);
                for (int i = 0; i < daysBetween; i++) {
                    for (String s : reservedHall.getStartTimeList()) {
                        Optional<Hall> optionalHall = hallRepository.findById(reservedHall.getHallId());
                        if (!optionalHall.isPresent()) {
                            throw new ResourceNotFoundException("Hall not found!");
                        }
                        Optional<MovieAnnouncement> optionalMovieAnnouncement = movieAnnouncementsRepository.findById(movieSessionDto.getMovieAnnouncementId());
                        if (!optionalMovieAnnouncement.isPresent()){
                            throw new ResourceNotFoundException("Movie Announcement not found!!");
                        }
                        try {
                            SessionDate sessionDate = new SessionDate(Date.from(startDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                            SessionDate savedDate = sessionDateRepository.save(sessionDate);
                            SessionTime sessionTime = new SessionTime(new Time(new SimpleDateFormat(s).parse(s).getTime()), 0.0);
                            SessionTime savedtime = sessionTimeRepository.save(sessionTime);

                            MovieSession movieSession = new MovieSession(optionalHall.get(),
                                    savedDate,
                                    savedtime,
                                    null,
                                    optionalMovieAnnouncement.get()
                            );
                            movieSessionRepository.save(movieSession);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return ResponseEntity.ok(new ApiResponse("Success",true,null));
    }

    @Override
    public HttpEntity<?> deleteMovieSession(UUID id) {
        return null;
    }

    @Override
    public HttpEntity<?> editMovieSession(UUID id) {
        return null;
    }
}
