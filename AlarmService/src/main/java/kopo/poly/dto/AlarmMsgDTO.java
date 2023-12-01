package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record AlarmMsgDTO(

        Long msgSeq, // ALARM_MSG 테이블의 PK
        String userId, // 회원 아이디
        String url, // 메세지 이동 url
        String title,// 제목
        String content, // 내용
        String msgTime, // Token 최근 사용 날짜

        List<String> deviceDTO

) {
}
