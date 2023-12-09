package kopo.poly.service;

import kopo.poly.dto.MailCodeDTO;

public interface IMailService {

    /**
     회원가입시 메일 인증 보내기
     */
    int sendCode(MailCodeDTO mDTO) throws Exception;

    /**
     *
     * 비밀번호 찾기 임시 비밀번호 전송 코드
     * @param email
     * @return 성공여부 (성공하면 1 , 실패시 0)
     * @throws Exception
     */
    int findPassword(String email)throws Exception;

    // 메일 코드 보내기
    int findCode(MailCodeDTO mDTO)throws Exception;
}
