package uz.pdp.cinema_room_individual_project.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.enums.CastType;
import uz.pdp.cinema_room_individual_project.model.*;
import uz.pdp.cinema_room_individual_project.repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    HallRepository hallRepository;
    @Autowired
    PricecategoryRepository pricecategoryRepository;
    @Autowired
    RowRepository rowRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CastRepository castRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Override
    public void run(String... args) throws Exception {
//        Attachment attachment = new Attachment("att 1", "file", 5454L);
//        attachmentRepository.save(attachment);
//        AttachmentContent attachmentContent = new AttachmentContent(null, attachment);
//        attachmentContentRepository.save(attachmentContent);
//        Attachment attachment1 = new Attachment("zlxdfvbfkcnlkzxnc", "sddvcdzv", 3211321L);
//        attachmentRepository.save(attachment1);
//        AttachmentContent attachmentContent2 = new AttachmentContent(null, attachment);
//        attachmentContentRepository.save(attachmentContent2);
//
//        List<Cast> castList = new ArrayList<>(Arrays.asList(
//                new Cast("Aziz", attachment, CastType.CAST_ACTOR),
//                new Cast("Abror", attachment1, CastType.CAST_ACTOR),
//                new Cast("Avaz", null, CastType.CAST_ACTOR),
//                new Cast("Eldor", null, CastType.CAST_ACTOR)
//        ));
//        List<Cast> savedCastList = castRepository.saveAll(castList);
//        Distributor distributor = new Distributor("Distributor 1", "Very good", attachment1);
//
//
//        Movie movie = new Movie("Spiderman", "Good", 90, 30000.0, "youtube.com", attachment,
//                LocalDate.now(), 1000000.0,
//                50.0, distributor, null, castList);
//        movieRepository.save(movie);
    }
    }
