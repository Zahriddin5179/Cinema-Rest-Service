package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.CastDto;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.model.AttachmentContent;
import uz.pdp.cinema_room_individual_project.model.Cast;
import uz.pdp.cinema_room_individual_project.projection.CastProjection;
import uz.pdp.cinema_room_individual_project.repository.AttachmentContentRepository;
import uz.pdp.cinema_room_individual_project.repository.AttachmentRepository;
import uz.pdp.cinema_room_individual_project.repository.CastRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CastService {
    @Autowired
    CastRepository castRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public List<CastProjection> getAllCastsByMovieId(@PathVariable(required = true) UUID movieId) {
        return castRepository.findByMovieId(movieId);
    }

    public List<Cast> getAllCasts() {
        return castRepository.findAll();
    }

    public Cast castSave(MultipartFile multipartFile, CastDto castDto){
        try {
        Attachment attachment = attachmentRepository.save(new Attachment(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize()));
            attachmentContentRepository.save(new AttachmentContent(multipartFile.getBytes(),
                    attachment));
            Cast save = castRepository.save(new Cast(castDto.getFullName(), attachment,
                    castDto.getCastType()));
            return save;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteCast(UUID castId){
        castRepository.deleteById(castId);
    }

}
