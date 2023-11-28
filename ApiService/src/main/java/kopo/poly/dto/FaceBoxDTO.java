package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record FaceBoxDTO(

        float topLeftX, // 얼굴영역 왼쪽 꼭짓점 X좌표
        float topLeftY, // 얼굴영역 왼쪽 꼭짓점 Y좌표
        float faceHeight, // 얼굴영역 높이
        float faceWidth, // 얼굴영역 너비
        List<Float> landmark //

) {
}