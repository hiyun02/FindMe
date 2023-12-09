package kopo.poly.dto;

import lombok.Builder;

@Builder
public record UserInfoDTO(
        String userId, // 회원아이디
        String password, // 비밀번호
        String email, //이메일
        String userName,//이름
        String userDate,//가입일
        String addr1, // 주소
        String addr2, // 상세주소
        String userAlarm,// 알림 여부
        String roles //권한
){
}
