package uz.pdp.cinema_room_individual_project.controller;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.repository.AttachmentRepository;
import uz.pdp.cinema_room_individual_project.service.AttachmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/attachments")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @GetMapping
    public ResponseEntity<?>getAllAttachments(){
        List<Attachment> attachments = attachmentService.getAllAttachments();
        return ResponseEntity.ok(new ApiResponse("Succes",true,attachments));
    }
    @PostMapping
    public ResponseEntity<?> saveAttachment(@RequestPart("file")MultipartFile multipartFile){
        Attachment attachment = attachmentService.attachmentSave(multipartFile);
        return ResponseEntity.ok(new ApiResponse("Succesfully saved",true,attachment));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAttachment(@RequestParam(required = true,
            name = "attachment_id") UUID attachmentId) {
        attachmentService.deleteAttachment(attachmentId);
        return ResponseEntity.ok(new ApiResponse("succesfully deleted", true, null));
    }

}
