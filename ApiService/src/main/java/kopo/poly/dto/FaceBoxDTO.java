package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record FaceBoxDTO(

        Float topLeftX, // 얼굴영역 왼쪽 꼭짓점 X좌표
        Float topLeftY, // 얼굴영역 왼쪽 꼭짓점 Y좌표
        Float faceHeight, // 얼굴영역 높이
        Float faceWidth, // 얼굴영역 너비
        List<Float> landmark //

) {
}