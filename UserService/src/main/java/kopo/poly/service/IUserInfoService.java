package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserInfoService extends UserDetailsService {

    // 회원 정보 등록
    int insertUserInfo(UserInfoDTO pDTO)throws Exception;

    // 회원정보 조회 후 정보 반환
    UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception;

    // 아아디 중복 확인
    int idCheck(String userId)throws Exception;

    // 아이디 찾기
    String idFind(String email)throws Exception;

    //유저 정보 수정 == 마이페이지 수정
    int userUpdate(UserInfoDTO pDTO)throws Exception;

    // 유저 정보 삭제
    int userDelete(String userId)throws Exception;

    //비밀번호 확인
    int passWordCheck(UserInfoDTO pDTO)throws Exception;
}
