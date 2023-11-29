package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record AlarmMsgDTO(

        String title, // 메세지 제목
        String content, // 메세지 내용
        String url, // 메세지 이동 url


        String date,
        String alarmSeq, // AlarmMsg PK
        String pushToken, // 기기의 고유 토큰
        String userId // 유저 아이디
) {
}
