package kopo.poly.controller;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST"}, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/face/bucket")
public class BucketController  {

    private final IBucketService bucketService;

    @PostMapping(value = "uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FaceDTO> uploadFile(List<FaceDTO> pList) {
        log.info(this.getClass().getName()+"uploadFile Start!");
        log.info("uploadFile List 길아 : {}", pList.size());
        return bucketService.uploadFile(pList);
    }

    @DeleteMapping(value="deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        log.info(this.getClass().getName()+"deleteFile Start!");
        bucketService.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}