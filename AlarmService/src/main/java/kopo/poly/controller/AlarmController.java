package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.service.IAlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final IAlarmService alarmService;


    /**
     * 태그 값으로 알림하는 거 알아보기
     */


    /**
     * 사용자 아이디 받아서 기기 조회 후 메세지 보내기
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("sendMessing")
    public int sendMessing(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "메세지 보내기 시작");

        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.sendMessing(pDTO);
        log.info(getClass().getName() + "메세지 보내기 종료");

        return res;
    }

    /**
     * 사용자기기 고유 토큰 저장
     * @param request
     * @return
     * @throws Exception
     */

    @PostMapping("saveToken")
    public int saveToken(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        //
        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.saveToken(pDTO);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }

    /**
     * 사용자기기 고유 토큰 가져오기 즉 사용자 기기 조회
     * @param request
     * @return
     * @throws Exception
     */

    @PostMapping("getToken")
    public int getToken(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        //
        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.saveToken(pDTO);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }




    /**
     * 사용자의 과거 알람 조회
     * @param request
     * @return
     * @throws Exception
     */

    @PostMapping("getAlarm")
    public int getAlarm(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        //
        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.saveToken(pDTO);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }



    /**
     *  사용자의 과거 알람 전체 삭제
     * @param request
     * @return
     * @throws Exception
     */

    @PostMapping("deleteAllAlarm")
    public int deleteAllAlarm(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        //
        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.saveToken(pDTO);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }


    /**
     * 사용자의 과거 알람 1건 씩 기록 삭제
     * @param request
     * @return
     * @throws Exception
     */

    @PostMapping("deleteAlarm")
    public int deleteAlarm(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        //
        AlarmMsgDTO pDTO = AlarmMsgDTO.builder().build();
        int res = alarmService.saveToken(pDTO);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }
}
