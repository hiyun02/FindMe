package kopo.poly.controller;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST", "DELETE"}, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/face/bucket")
public class BucketController {

    private final IBucketService bucketService;

    @PostMapping(value = "uploadFile",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public FaceDTO uploadFile(@RequestPart String fileName, @RequestPart MultipartFile image) {

        log.info(this.getClass().getName() + ".uploadFile Start!");

        log.info("Object Storage 업로드를 위해 FaceService로 넘어온 이미지 : {}", image);
        log.info("Object Storage 업로드를 위해 FaceService로 넘어온 파일명 : {}", fileName);

        FaceDTO faceDTO = FaceDTO.builder().imageName(fileName).image(image).build();

        log.info(this.getClass().getName() + ".uploadFile End!");

        return Optional.ofNullable(bucketService.uploadFile(faceDTO)).orElseGet(() -> FaceDTO.builder().build());
    }

    @DeleteMapping(value = "deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        log.info(this.getClass().getName() + ".deleteFile Start!");
        bucketService.deleteFile(fileName);
        log.info(this.getClass().getName() + ".deleteFile End!");
        return ResponseEntity.ok(fileName);
    }

}