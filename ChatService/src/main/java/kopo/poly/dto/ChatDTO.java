package kopo.poly.dto;

import lombok.Builder;

@Builder
public record ChatDTO(
        String roomName,
        String sendId,
        String receiveId,


        // redis 저장 정보
        String sendTime,
        String chatContent
) {
}
