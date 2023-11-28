package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// Face -> Subject -> Group 순으로 포함되는 개념임
// Face를 찾거나 지정하기 위해선 Subject가, Subject를 찾거나 지정하기 위해선 Group이 요구됨
@FeignClient(name = "RecAPI", url = "https://apis.openapi.sk.com/nugufacecan/v1/recognize")
public interface IRecAPIService {

    /**
     * @param group_id  Face를 생성할 Subject의 소속 Group의 식별자
     * @param subject_id  Face를 생성할 Subject의 식별자
     * @param face_name  생성할 Face 이름 (한글 미지원)
     * @param image  생성할 Face image (base64)
     * @return face 생성
     */
      @PostMapping(value = "/",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
      FaceDTO createFace(
              @RequestHeader("group-id") String group_id,
              @RequestHeader("subject-id") String subject_id,
              @RequestHeader("face-name") String face_name,
              @RequestParam("image") String image
              );

}
