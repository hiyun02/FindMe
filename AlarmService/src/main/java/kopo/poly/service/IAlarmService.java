package kopo.poly.service;

import kopo.poly.dto.AlarmMsgDTO;

public interface IAlarmService {


    // 사용자별 기기 Token 저장
    int saveToken(AlarmMsgDTO pDTO)throws Exception;

    // 메세지 보내기
    int sendMessing(AlarmMsgDTO pDTO)throws Exception;

}
