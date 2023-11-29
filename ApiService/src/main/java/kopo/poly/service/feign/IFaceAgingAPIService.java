package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

// Face Recognize API : 이미지가 입력되면, 관리 중인 Face 중 가장 유사한 것을 찾아서 반환함ㅁㄴ
@FeignClient(name = "RecAPI", url = "https://api.replicate.com/v1/predictions")
public interface IFaceAgingAPIService {

    /**
     */
      @PostMapping(value = "/",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
      List<FaceDTO> getRecognizedList(
              @RequestHeader("Content-Type") String contentType,
              @RequestHeader("Authorization") String authorization,
              @RequestHeader("version") String version,
              @RequestParam("input") Map<String, String> input
              );

}
