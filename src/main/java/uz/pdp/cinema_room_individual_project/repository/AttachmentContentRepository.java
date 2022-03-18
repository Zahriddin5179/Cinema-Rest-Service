package uz.pdp.cinema_room_individual_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.model.AttachmentContent;

import java.util.UUID;

@Component
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,UUID> {
    AttachmentContent findByAttachment(Attachment attachment);
}
