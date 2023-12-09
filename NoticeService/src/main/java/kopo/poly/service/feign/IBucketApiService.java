package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "BucketAPIService", url = "http://localhost:17000/face/bucket")
public interface IBucketApiService {

    /**
     * @param image 업로드할 이미지
     * @param fileName 이미지 명
     * @return
     */
      @PostMapping (value = "/uploadFile",
              consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
              produces = MediaType.APPLICATION_JSON_VALUE)
      FaceDTO uploadFile(
            @RequestPart("image") MultipartFile image,
            @RequestParam("fileName") String fileName
    );

    /**
     * @param fileName 삭제할 이미지 명
     */
    @PostMapping (value = "/deleteFile",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteFile(
            @RequestParam("fileName") String fileName
    );

}
