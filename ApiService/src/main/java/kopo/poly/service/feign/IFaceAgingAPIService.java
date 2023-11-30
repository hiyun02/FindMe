package kopo.poly.service.feign;

import feign.Param;
import feign.RequestLine;
import kopo.poly.dto.FaceAgingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

// Face Aging API : 이미지가 입력되면, 관리 중인 Face 중 가장 유사한 것을 찾아서 반환함ㅁㄴ
@FeignClient(name = "FaceAgingAPI", url = "https://api.replicate.com/v1/predictions")
public interface IFaceAgingAPIService {

    /**
     *
     */
    @PostMapping(value = "")
    FaceAgingDTO getAgedFaceImage(
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("version") String version,
            @Param("input") Map<String, String> input
    );

}
