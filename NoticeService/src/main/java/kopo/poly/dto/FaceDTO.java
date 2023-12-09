package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

// SK Open API 제공 Nugu API 사용을 위한 FaceDTO.
// 제공되는 3계층(Group-Subject-Face) 구조 및 얼굴 분석 기눙에 필요한 변수 정의
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record FaceDTO(

        //---Group Layer---
        String group_id, // group 식별자
        String group_name, // group 이름

        //---Subject Layer---
        String subject_id, // subject 식별자
        String subject_name, // subject 이름

        //---Face Layer---
        MultipartFile image, // 요청 이미지 (타입 String으로 변경?)
        String face_id, // 이미지 식별자
        String face_name, // 이미지 이름
        FaceBoxDTO Box, // 이미지 상에서의 얼굴 위치
        Double face_score, // 얼굴에 대한 Confidence Value
        String expression, // 인간의 기본적인 6가지 감정을 토대로 top-1 표정 리턴
        Double expression_score, // 감지된 표정에 대한 Confidence Value
        Float age, // 사진을 통해 유추한 나이(apparent age)
        String gender, // 예측한 성별, 아이의 경우 unknown 리턴
        String attribute, // 안경, 마스크 착용여부 등등...
        Integer image_width, // 이미지 너비
        Integer image_height, // 이미지 높이
        Integer engine_version, // 인식 엔진의 버전

        //---Face Recognize---
        Double threshold,
//      threshold : 0 인 경우 동일인물, 1 인 경우 다른 인물에 가까움
//      Default : 0.32
//      특정 Site 마다 threshold 를 조절하여 precision, recall tuning 가능
//      threshold 가 작은 값일수록 precision이 높아지지만, recall 이 낮아짐
//      threshold 가 높은 값일수록 precision이 낮아지지만, recall 이 높아짐(다양한 얼굴의 variation 인식이 필요한 경우 조절)
        String distance, // 가장 비슷한 얼굴과의 거리 값(작을수록 유사성 높음)
        FaceBoxDTO face_box, // 인식된 얼굴의 이미지 상에서의 위치
//      각 표정에 대한 Confidence Value (fear, surprised, neutral, angry, smile, sad, etc)
        Map<String, Double> expression_raw,
        String transaction_id, // 해당 결과에 대한 식별 ID

        //버킷 저장 정보
        String imageUrl,
        String imageName
) {
}