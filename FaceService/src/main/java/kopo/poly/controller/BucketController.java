package kopo.poly.controller;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/face/bucket")
public class BucketController  {

    private final IBucketService bucketService;

    @PostMapping("uploadFile")
    public List<FaceDTO> uploadFile(List<FaceDTO> pList) {
        log.info(this.getClass().getName()+"uploadFile Start!");
        log.info("uploadFile List 길아 : {}", pList.size());
        return bucketService.uploadFile(pList);
    }

    @DeleteMapping("deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        log.info(this.getClass().getName()+"deleteFile Start!");)
        bucketService.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}