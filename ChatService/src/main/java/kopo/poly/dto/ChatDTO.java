package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record ChatDTO(
        String roomName,
        String sendId,
        String receiveId,


        // redis 저장 정보
        String sendTime,
        String chatContent,

        List<ChatDTO> rList // 채팅방 전체 조회를 위해
) {
}
