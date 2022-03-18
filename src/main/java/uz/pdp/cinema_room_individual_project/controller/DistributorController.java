package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.DistributorDto;
import uz.pdp.cinema_room_individual_project.model.Distributor;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.service.DistributorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/distributors")
public class DistributorController {
    @Autowired
    DistributorService distributorService;

    @GetMapping
    public HttpEntity distributors(){
        List<Distributor> distributorList = distributorService.getAllDistributors();
        ApiResponse response = new ApiResponse("succes", true, distributorList);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?> saveDistributor(@RequestPart("file")MultipartFile multipartFile,
                                             @RequestPart("json")DistributorDto distributorDto){
        Distributor distributor = distributorService.saveDistributor(multipartFile, distributorDto);
        if (distributor!=null){
            return ResponseEntity.ok(new ApiResponse("succes",true,distributor));
        }
            return ResponseEntity.ok(new ApiResponse("fail",false,null));
    }
    @DeleteMapping
    public ResponseEntity<?> deleteDistributor(@RequestParam(required = true,
            name = "distributor_id")UUID distributorId){
        distributorService.deleteDistributor(distributorId);
        return ResponseEntity.ok(new ApiResponse("succesfully deleted",true,null));
    }
}
