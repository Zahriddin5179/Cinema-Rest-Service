package uz.pdp.cinema_room_individual_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.DistributorDto;
import uz.pdp.cinema_room_individual_project.model.Attachment;
import uz.pdp.cinema_room_individual_project.model.AttachmentContent;
import uz.pdp.cinema_room_individual_project.model.Cast;
import uz.pdp.cinema_room_individual_project.model.Distributor;
import uz.pdp.cinema_room_individual_project.projection.CastProjection;
import uz.pdp.cinema_room_individual_project.projection.DistributorProjection;
import uz.pdp.cinema_room_individual_project.repository.AttachmentContentRepository;
import uz.pdp.cinema_room_individual_project.repository.AttachmentRepository;
import uz.pdp.cinema_room_individual_project.repository.CastRepository;
import uz.pdp.cinema_room_individual_project.repository.DistributorRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DistributorService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    DistributorRepository distributorRepository;

    public List<Distributor> getAllDistributors(){
        return  distributorRepository.findAll();
    }

    public Distributor saveDistributor(MultipartFile multipartFile, DistributorDto distributorDto){
        try {
        Attachment attachment = attachmentRepository.save(new Attachment(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize()));
           attachmentContentRepository.save(new AttachmentContent(multipartFile.getBytes(),
                    attachment));
           return distributorRepository.save(new Distributor(distributorDto.getName(),
                    distributorDto.getDescription(),attachment));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteDistributor(UUID distributorId){
        distributorRepository.deleteById(distributorId);
    }

}
