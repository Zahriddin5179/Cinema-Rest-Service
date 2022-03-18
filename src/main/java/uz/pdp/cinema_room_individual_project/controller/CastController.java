package uz.pdp.cinema_room_individual_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room_individual_project.dto.CastDto;
import uz.pdp.cinema_room_individual_project.dto.DistributorDto;
import uz.pdp.cinema_room_individual_project.model.Cast;
import uz.pdp.cinema_room_individual_project.payload.ApiResponse;
import uz.pdp.cinema_room_individual_project.projection.CastProjection;
import uz.pdp.cinema_room_individual_project.service.CastService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/casts")
public class CastController {
    @Autowired
    CastService castService;

    @GetMapping
    public HttpEntity getAllCasts() {
        List<Cast> allCasts = castService.getAllCasts();
        ApiResponse response = new ApiResponse("succes", true, allCasts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    public HttpEntity getAllCastsByMovieId(@PathVariable(required = true) UUID movieId) {
        List<CastProjection> allCastsByMovieId = castService.getAllCastsByMovieId(movieId);

        ApiResponse res = new ApiResponse("success", true, allCastsByMovieId);

        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<?> castSave(@RequestPart("file") MultipartFile multipartFile,
                                      @RequestPart("json") CastDto castDto) {
        Cast cast = castService.castSave(multipartFile, castDto);
        if (cast != null) {
            return ResponseEntity.ok(new ApiResponse("Succesfully saved", true, cast));
        }
        return ResponseEntity.ok(new ApiResponse("failed ", false, null));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDistributor(@RequestParam(required = true,
            name = "cast_id") UUID castId) {
        castService.deleteCast( castId);
        return ResponseEntity.ok(new ApiResponse("succesfully deleted", true, null));
    }


}
