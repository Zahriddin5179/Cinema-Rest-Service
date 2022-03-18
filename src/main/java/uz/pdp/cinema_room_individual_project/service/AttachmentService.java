package uz.pdp.cinema_room_individual_project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.model.AttachmentContent;
import uz.pdp.cinema_room_individual_project.repository.AttachmentContentRepository;
import uz.pdp.cinema_room_individual_project.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }
    public Attachment attachmentSave(MultipartFile multipartFile){
        try {
        Attachment attachment = attachmentRepository.save(new Attachment(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize()));
            attachmentContentRepository.save(new AttachmentContent(multipartFile.getBytes(),
                    attachment));
        return attachment;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteAttachment(UUID attachmentId){
        attachmentRepository.deleteById(attachmentId);
    }
}
