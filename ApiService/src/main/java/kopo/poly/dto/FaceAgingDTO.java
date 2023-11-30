package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

// SK Open API 제공 Nugu API 사용을 위한 FaceDTO.
// 제공되는 3계층(Group-Subject-Face) 구조 및 얼굴 분석 기눙에 필요한 변수 정의
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record FaceAgingDTO(
        //---Face Aging---
        String image,
        String target_age,
        Map<String, String> map, // "image" : "http경로", "target_age" :
        String output // 결과 이미지 url
) {
}