package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.dto.DeviceDTO;
import kopo.poly.dto.TokenDTO;
import kopo.poly.service.IAlarmService;
import kopo.poly.service.ITokenAPIService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final IAlarmService alarmService;

    private final ITokenAPIService tokenAPIService;

    private final String HEADER_PREFIX = "Bearer ";

    /**
     * 태그 값으로 알림하는 거 알아보기
     */


    /**
     * 사용자기기 고유 토큰 저장
     * @param request userId, token값
     * @return 성공여부 (성공 : 1, 실패 : 0)
     * @throws Exception
     */

    @PostMapping("saveToken")
    public int saveToken(HttpServletRequest request, @CookieValue(value = "${jwt.token.access.name}") String token) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);


        String pushToken = CmmUtil.nvl(request.getParameter("pushToken"));
        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값

        log.info("userId : " + userId);
        log.info("pushToken : " + pushToken);


        DeviceDTO pDTO = DeviceDTO.builder()
                .userId(userId)
                .pushToken(pushToken)
                .build();



        int res = alarmService.saveToken(pDTO);

        log.info("res : " + res);
        if (res == 1) {
            log.info("저장 성공");
        } else {
            log.info("저장 실패");
        }

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }



    /**
     * 사용자 아이디 받아서 기기 조회 후 메세지 보내기
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("sendMessing")
    public int sendMessing(HttpServletRequest request, @CookieValue(value = "${jwt.token.access.name}") String token) throws Exception {
        log.info(getClass().getName() + "메세지 보내기 시작");

        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);

        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값
        List<String> deviceDTO = Optional.ofNullable(getDevice(userId)).orElseGet(ArrayList::new);

        AlarmMsgDTO pDTO = AlarmMsgDTO.builder()
                .deviceDTO(deviceDTO)
                .userId(userId)
                .build();

        int res = alarmService.sendMessing(pDTO);
        log.info(getClass().getName() + "메세지 보내기 종료");

        return res;
    }


    /**
     *
     * @param userId 회원아이디
     * @return 기기의 토큰값이 있는 List
     * @throws Exception
     */
    @PostMapping("getDevice")
    private List<String> getDevice(String userId)throws Exception {
        log.info(getClass().getName() + "사용자의 기기 가져오기 시작");

        userId = "1111";
        List<String> rList = Optional.ofNullable(alarmService.getDevice(userId)).orElseGet(ArrayList::new);

        log.info(getClass().getName() + "사용자의 기기 가져오기 종료");

        return rList;
    }


    /**
     * 사용자 알람 조회 함수 만들기
     */



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

        log.info(getClass().getName() + "토큰 저장 종료");

        return 0;
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

        log.info(getClass().getName() + "토큰 저장 종료");

        return 0;
    }
}
