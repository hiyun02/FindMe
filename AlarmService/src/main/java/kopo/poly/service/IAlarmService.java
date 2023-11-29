package kopo.poly.service;

import kopo.poly.dto.AlarmMsgDTO;

public interface IAlarmService {

    // 메세지 보내기
    int sendMessing(AlarmMsgDTO pDTO)throws Exception;

    // 사용자별 기기 메시지 저장
    int saveToken(AlarmMsgDTO pDTO)throws Exception;
}
